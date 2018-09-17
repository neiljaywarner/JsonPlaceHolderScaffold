package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import com.neiljaywarner.jsonplaceholderscaffold.model.Photo



class PhotosViewModel(val repo: PhotoRepository) : ViewModel() {
    private var photos: MutableLiveData<List<Photo>>? = null

    fun init(albumId: Int) {
        // TODO: this was in Google's guide, but delete it if not needed
        // thought it was related to rotation
        photos?.run {
            photos = repo.getPhotos(albumId)
        }
    }

    fun getPhotos(albumId: Int): MutableLiveData<List<Photo>> {
        return repo.getPhotos(albumId)
    }

    fun isLoading(): LiveData<Boolean> {
        return repo.isLoading
    }
}

