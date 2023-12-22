package com.example.test.presentation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.common.Constants.FILE_PATH
import com.example.test.common.Resource
import com.example.test.data.DbRepositoryImpl
import com.example.test.data.MyDatabase
import com.example.test.domain.get_fields.GetFieldsUseCase
import com.example.test.domain.get_task.GetInfoByVkIdUseCase
import com.example.test.domain.model.InfoListState
import com.example.test.domain.model.Message
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File

enum class ConState() {
    NOT_CONNECTED(),
    CONNECTING(),
    CONNECTED()
}

//@HiltViewModel
class DbViewModel () : ViewModel(), USBConnectionObserverListener {

    var storageIsConnectedState by mutableStateOf(ConState.NOT_CONNECTED)
        private set
    fun setStorageConnection(newState: ConState) {
        storageIsConnectedState = newState
    }

    init {
        USBConnectionObserver.addObserver(this)
    }

    override fun onUSBConnected() {
        // Обработка события подключения по USB
        Log.d("USBConnectionReceiver", "onUSBConnected")
        setStorageConnection(ConState.CONNECTING)
    }

    override fun onUSBDisconnected() {
        // Обработка события отключения по USB
        Log.d("USBConnectionReceiver", "onUSBDisconnected")
        setStorageConnection(ConState.NOT_CONNECTED)
        setDbInited(false)
        setHasColumns(false)
        setColumnList(emptyList())
    }

    override fun onCleared() {
        super.onCleared()
        USBConnectionObserver.removeObserver(this)
    }

    lateinit var repository: DbRepositoryImpl

    lateinit var scrollMsgState: LazyListState
        private set
    fun scrollMsgStateInit(newListState: LazyListState) {
        scrollMsgState = newListState
    }

    var columnListState by mutableStateOf(emptyList<String>())
        private set
    fun setColumnList(newState: List<String>) {
        columnListState = newState
    }

    var chosenColumnState by mutableStateOf("")
        private set
    fun setChosenColumn(newState: String) {
        chosenColumnState = newState
    }

    var expandedState by mutableStateOf(false)
        private set
    fun reverseExpanded() {
        expandedState = !expandedState
    }

    var vkIdState by mutableStateOf("242")
        private set
    fun setVkId(newState: String) {
        vkIdState = newState
    }

    var phoneState by mutableStateOf("79219180009")
        private set
    fun setPhone(newState: String) {
        phoneState = newState
    }

    fun checkStorageIsConnected(path: String): Boolean {
        return File(path).exists()
    }

    var dbIsInitedState by mutableStateOf(false)
        private set
    fun setDbInited(newState: Boolean) {
        dbIsInitedState = newState
    }

    var hasColumnsState by mutableStateOf(false)
        private set
    fun setHasColumns(newState: Boolean) {
        hasColumnsState = newState
    }

    fun initDb(context: Context, path: String = FILE_PATH) {
        if (!dbIsInitedState) {
            var isConnected = false
            while (!isConnected) {
                Thread.sleep(500)
                if (checkStorageIsConnected(path)) {
                    setStorageConnection(ConState.CONNECTED)
                    repository = DbRepositoryImpl(MyDatabase(context))
                    isConnected = true
                } else {
                    setStorageConnection(ConState.CONNECTING)
                }
            }
            setDbInited(true)
        }
    }

    private val _infoList = mutableStateOf(InfoListState(tasks = listOf("Revenger13 \uD83D\uDE0F")))
    val infoList: State<InfoListState> = _infoList

    //var infoMessages by mutableStateOf(mutableListOf(Message(true, listOf("Ладно, Пикачу!")), Message(false, listOf("Сокруши их!"))))
    var infoMessages by mutableStateOf(mutableListOf(Message(true, listOf("Добрый день! Введите данные для поиска информации в базе данных на флеш-накопителе."))))
        private set
    fun addToMessages(newState: Message) {
        infoMessages.add(newState)
    }

    fun addMyMessage(message: String) {
        addToMessages(Message(fromApp = false, message = listOf(message)))
    }

    fun getColumnNames() {
        GetFieldsUseCase(repository).getColumnNames().onEach { result ->
            when (result) {
                is Resource.Empty -> {
                    Log.d("GetColumns", "Empty")
                }
                is Resource.Success -> {
                    Log.d("GetColumns", "Success: ${result.data}")
                    setColumnList(result.data!!)
                    setChosenColumn(columnListState.first())
                    setHasColumns(true)
                }
                is Resource.Error -> {
                    Log.d("GetColumns", "Error: ${result.data}")
                }
                is Resource.Loading -> {
                    Log.d("GetColumns", "Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getInfoByOneField(fields: List<String>, param: String = "", arg: String = "") {
        if (param.isBlank() || arg.isBlank()) {}
        else {
            addMyMessage(message = "$param: $arg")
            GetInfoByVkIdUseCase(repository).getInfoByOneField(fields, param, arg).onEach { result ->
                when (result) {
                    is Resource.Empty -> {
                        _infoList.value = InfoListState(isEmpty = true)
                        addToMessages(Message(message = listOf("Не найдено"), time = result.time!!))
                    }
                    is Resource.Success -> {
                        _infoList.value = InfoListState(tasks = result.data!!)
                        addToMessages(Message(message = result.data, time = result.time!!))
                    }
                    is Resource.Error -> {
                        _infoList.value = InfoListState(error = "Err ${result.data}")
                    }
                    is Resource.Loading -> {
                        _infoList.value = InfoListState(tasks = _infoList.value.tasks, isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}