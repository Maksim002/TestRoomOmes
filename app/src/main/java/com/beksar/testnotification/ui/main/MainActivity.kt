package com.beksar.testnotification.ui.main

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.beksar.testnotification.R
import com.beksar.testnotification.adapter.listener.RecyclerViewListener
import com.beksar.testnotification.adapter.model.RecyclerModel
import com.beksar.testnotification.adapter.model.ResultRecyclerModel
import com.beksar.testnotification.extension.roomModel
import com.beksar.testnotification.extension.writingDatabase
import com.beksar.testnotification.service.model.ModelRoom
import com.beksar.testnotification.utils.LoadingAlert
import com.beksar.testnotification.utils.Pagination
import com.example.testroomkotlin.AppDataBase
import com.example.testtask.adapter.RecyclerAdapter
import com.timelysoft.tsjdomcom.service.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity(), RecyclerViewListener {
    private lateinit var db: AppDataBase
    private var viewMode = ActivityViewModel()
    lateinit var alert: LoadingAlert
    private lateinit var adapterMy: RecyclerAdapter
    private var itemSize: Int = 9
    private var listPosition: Int = 0
    private var isLoading = false
    private var firstStart = true
    val handler = Handler()
    //кол 100
    private val list: ArrayList<RecyclerModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDataBase.instance(this)
        alert = LoadingAlert(this)
        viewMode = ActivityViewModel()
        adapterMy = RecyclerAdapter(this)
        initRecyclerView()
    }

    //Слушатель RecyclerView
    override fun setOnClickListener(item: RecyclerModel) {
        Toast.makeText(this, "Страна: " + item.country.toString(), Toast.LENGTH_LONG).show()
    }

    //Логика работы с RecyclerView
    private fun initRecyclerView() {
        alert.show()
        viewMode.news().observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    val item: ArrayList<ResultRecyclerModel> = arrayListOf()
                    for (key in it.data!!.data!!.keys) {
                        item.add(ResultRecyclerModel(it.data.data?.get(key)!!.country.toString(), it.data.data?.get(key)!!.region.toString(), key))
                    }
                    //Добавление данных в бд
                    writingDatabase(item, db)

                    //Задержка для того чтобы база успела заполница
                    handler.postDelayed(Runnable { // Do something after 5s = 500ms
                        loadNextPage()
                        alert.hide()
                    }, 1000)
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(this, "Ошибка: " + it.msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        //Слушатель прокрутки списка
        recycler_view.addOnScrollListener(object :
            Pagination(recycler_view.layoutManager as LinearLayoutManager?) {
            override fun logMoreItem() {
                alert.show()
                isLoading = true
                Handler().postDelayed({
                    if (itemSize != 99) {
                        itemSize += 9
                        loadNextPage()
                    }
                    alert.hide()
                }, 700)
            }

            override fun getTiralPegers(): Int { return itemSize }
            override fun isLoginpage(): Boolean { return isLoading }
            override fun isLoginding(): Boolean { return isLoading }
        })
    }

    //Проверяет была ли прокрутка списка
    fun loadNextPage() {
        if (isLoading) {
            paginationRecycler()
        } else {
            paginationRecycler()
        }
    }

    //Фу-я добовлет в список данные
    private fun paginationRecycler() {
        for (i in list.size..itemSize) {
            if (i <= itemSize) {
                listPosition++
                list.add(RecyclerModel(roomModel[listPosition - 1].country, roomModel[listPosition - 1].region, roomModel[listPosition - 1].key))
                if (firstStart) {
                    adapterMy.update(list)
                    recycler_view.adapter = adapterMy
                    firstStart = false
                } else {
                    adapterMy.addItem(list)
                }
                isLoading = false
            }
        }
    }
}