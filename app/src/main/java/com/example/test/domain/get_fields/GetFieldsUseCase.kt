package com.example.test.domain.get_fields

import com.example.test.common.Resource
import com.example.test.data.DbRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

class GetFieldsUseCase(
    private val repository: DbRepositoryImpl
) {
    fun getColumnNames(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val columnFields: List<String>
            val time = measureTimeMillis {
                columnFields = repository.getColumnNames()
            }
            if (columnFields.isEmpty()) {
                emit(Resource.Empty(time))
            } else {
                emit(Resource.Success(columnFields, time))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}