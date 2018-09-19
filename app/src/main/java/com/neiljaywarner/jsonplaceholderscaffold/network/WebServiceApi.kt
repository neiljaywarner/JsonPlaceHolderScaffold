package com.neiljaywarner.jsonplaceholderscaffold.network


import com.neiljaywarner.jsonplaceholderscaffold.model.Photo

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServiceApi {


    // e.g https://jsonplaceholder.typicode.com/albums/1/photos
    // or https://jsonplaceholder.typicode.com/photos/?albumId=3
    @GET("/albums/{albumId}/photos")
    fun getPhotosByAlbum(@Path("albumId") albumId: Int): Deferred<List<Photo>>


}

