package com.neiljaywarner.jsonplaceholderscaffold.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.neiljaywarner.jsonplaceholderscaffold.model.Photo;

@Database(entities = {Photo.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

}