package com.beksar.testnotification.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beksar.testnotification.service.result.ExampleModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import java.lang.Exception

class ActivityViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun news(): LiveData<ResultStatus<ExampleModel>>{
        return repository.news()
    }
}