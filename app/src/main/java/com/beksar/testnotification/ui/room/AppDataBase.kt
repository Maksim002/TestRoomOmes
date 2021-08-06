package com.example.testroomkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beksar.testnotification.service.model.ModelRoom

@Database (entities = [ModelRoom::class] ,version = 3)
abstract class AppDataBase : RoomDatabase() {
    abstract fun appDataBase() : MDao

    companion object{
        @Volatile
        private var instance: AppDataBase? = null

        fun instance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "Room"
                ).build()
                this.instance = instance
                instance
            }

        }
    }
}