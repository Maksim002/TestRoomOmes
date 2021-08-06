package com.beksar.testnotification.service.result

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ExampleModel (
    @SerializedName("status")
    @Expose
    var status: String? = null,


    @SerializedName("status-code")
    @Expose
    var statusCode: Int? = null,


    @SerializedName("version")
    @Expose
    var version: String? = null,


    @SerializedName("total")
    @Expose
    var total: Int? = null,


    @SerializedName("limit")
    @Expose
    var limit: Int? = null,


    @SerializedName("offset")
    @Expose
    var offset: Int? = null,


    @SerializedName("access")
    @Expose
    var access: String? = null,

    @SerializedName("data")
    @Expose
    var data : Map<String, Data>? = null
)