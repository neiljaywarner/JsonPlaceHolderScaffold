package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import com.neiljaywarner.jsonplaceholderscaffold.model.Photo



class PhotosViewModel(private val repo: PhotoRepository) : ViewModel() {

    // TODO: use viewmodel to handle rotation by keeping its livedata alive.
    fun getPhotos(albumId: Int): MutableLiveData<List<Photo>> {
        return repo.getPhotos(albumId)
    }

    fun isLoading(): LiveData<Boolean> {
        return repo.isLoading
    }
}

