package com.neiljaywarner.jsonplaceholderscaffold.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.neiljaywarner.jsonplaceholderscaffold.model.Photo

@Database(entities = [Photo::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    //TODO: don't forget to look at https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
}