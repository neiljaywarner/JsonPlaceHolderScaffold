package com.neiljaywarner.jsonplaceholderscaffold.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Query
import android.arch.persistence.room.RawQuery

import com.neiljaywarner.jsonplaceholderscaffold.model.Photo
import java.sql.Date


abstract class PhotoDao: BaseDao<Photo> {
    @Query("SELECT * FROM Photo")
    abstract fun getData(): LiveData<List<Photo>>
    //TOOD: where albumid=aulbumid

    @Query("SELECT count(id) FROM Photo WHERE albumId = :albumId AND lastRefresh > :lastRefreshMax")
    abstract fun getCountFreshRows(albumId: Int, lastRefreshMax: Long): Int

    @Query("SELECT id from Photo WHERE albumId = :albumId AND lastRefresh > :lastRefreshMax")
    abstract fun rowId(albumId: Int, lastRefreshMax: Long) : Int?

    @Query("SELECT datetime('now')")
    abstract fun getDate() : Date
}

//from florina's article
//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
// also see:
// https://proandroiddev.com/the-missing-google-sample-of-android-architecture-components-guide-c7d6e7306b8f

// https://gist.github.com/tinmegali/d4a477785f01e57066915a44543db6ed
// if we need the refresh as date, not sure why we would want the overhead