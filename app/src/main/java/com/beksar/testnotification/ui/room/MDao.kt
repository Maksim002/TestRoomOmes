package com.example.testroomkotlin

import androidx.room.*
import com.beksar.testnotification.service.model.ModelRoom
import com.beksar.testnotification.service.result.ExampleModel

@Dao
interface MDao{

    @Query("SELECT * FROM modelroom")
    fun getAllModel():List<ModelRoom>

    @Insert
    fun insert(word: ModelRoom)

    @Query("DELETE FROM modelroom ")
    fun deleteAllModel()

    @Insert
    fun insertModel(modelRoom: ModelRoom)

    @Delete
    fun deleteWord(word: ModelRoom)

    @Update
    fun update(word: ModelRoom)
}