package com.beksar.testnotification.extension

import android.os.AsyncTask
import com.beksar.testnotification.adapter.model.RecyclerModel
import com.beksar.testnotification.adapter.model.ResultRecyclerModel
import com.beksar.testnotification.service.model.ModelRoom
import com.beksar.testnotification.ui.main.MainActivity
import com.example.testroomkotlin.AppDataBase
import kotlinx.coroutines.*

var roomModel: ArrayList<RecyclerModel> = arrayListOf()

fun writingDatabase(item: ArrayList<ResultRecyclerModel>, db: AppDataBase){
    roomModel.clear()

    CoroutineScope(Dispatchers.IO).launch {
        db.appDataBase().deleteAllModel()
        for (j in 1..item.size) {
            val dataItem = ModelRoom(j-1, item[j-1].key, item[j-1].country, item[j-1].region)
            db.appDataBase().insertModel(dataItem)
            db.appDataBase().getAllModel().forEach {
                roomModel.add(RecyclerModel(it.country, it.region, it.key))
            }
        }
    }
}