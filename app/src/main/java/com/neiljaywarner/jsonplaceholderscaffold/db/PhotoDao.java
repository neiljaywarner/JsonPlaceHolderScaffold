package com.neiljaywarner.jsonplaceholderscaffold.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.neiljaywarner.jsonplaceholderscaffold.model.Photo;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PhotoDao {
    @Insert(onConflict = REPLACE)
    int save(Photo photo);

    @Insert(onConflict = REPLACE)
    int saveAll(List<Photo> photos);

    @Query("SELECT * FROM Photo WHERE id = :albumId")
    LiveData<List<Photo>> load(int albumId);
}
/*
@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);
    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(int userId);
}

 */