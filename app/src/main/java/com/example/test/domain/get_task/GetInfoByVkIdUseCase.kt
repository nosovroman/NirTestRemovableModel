package com.example.test.domain.get_task

import com.example.test.common.Resource
import com.example.test.data.DbRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

class GetInfoByVkIdUseCase(
    private val repository: DbRepositoryImpl
) {
    fun getInfoByOneField(fields: List<String>, param: String, arg: String): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val currentTask: List<String>
            val time = measureTimeMillis {
                currentTask = repository.getInfoByOneField(fields, param, arg)
            }
            if (currentTask.isEmpty()) {
                emit(Resource.Empty(time))
            } else {
                emit(Resource.Success(currentTask, time))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}