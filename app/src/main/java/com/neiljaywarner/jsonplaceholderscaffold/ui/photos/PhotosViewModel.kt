package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import com.neiljaywarner.jsonplaceholderscaffold.model.Photo



class PhotosViewModel(val repo: PhotoRepository) : ViewModel() {
    private var photos: MutableLiveData<List<Photo>>? = null

    fun init(albumId: Int) {
        photos?.run {
            photos = repo.getPhotos(albumId)
        }
    }

    fun getPhotos(albumId: Int): MutableLiveData<List<Photo>>? {
        return repo.getPhotos(albumId)
    }
}

