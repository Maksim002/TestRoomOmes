package com.beksar.testnotification.service.model

import androidx.room.*

@Entity
class ModelRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "key")
    var key: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null,

    @ColumnInfo(name = "region")
    var region: String? = null
)


