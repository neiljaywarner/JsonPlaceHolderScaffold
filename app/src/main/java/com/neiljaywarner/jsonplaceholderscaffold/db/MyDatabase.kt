package com.neiljaywarner.jsonplaceholderscaffold.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.neiljaywarner.jsonplaceholderscaffold.model.Photo

@Database(entities = [Photo::class], version = 2)
abstract class MyDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MyDatabase::class.java, "mydatabase.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}