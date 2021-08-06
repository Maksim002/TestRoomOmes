package com.timelysoft.tsjdomcom.service


import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class NetworkRepository {

    fun news() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().news()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }
}