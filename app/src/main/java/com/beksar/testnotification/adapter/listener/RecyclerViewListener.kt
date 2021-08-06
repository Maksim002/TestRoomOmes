package com.beksar.testnotification.adapter.listener

import com.beksar.testnotification.adapter.model.RecyclerModel

interface RecyclerViewListener {
    fun setOnClickListener(item: RecyclerModel)
}