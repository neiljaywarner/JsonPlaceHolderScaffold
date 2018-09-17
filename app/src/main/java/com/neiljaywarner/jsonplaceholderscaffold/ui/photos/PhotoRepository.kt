package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.util.Log
import com.neiljaywarner.jsonplaceholderscaffold.model.Photo
import com.neiljaywarner.jsonplaceholderscaffold.network.ServiceGenerator
import com.neiljaywarner.jsonplaceholderscaffold.network.WebServiceApi
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class PhotoRepository {
    //TODO: pass this in via constructor injection
    private val webservice = ServiceGenerator.createDeferredService(WebServiceApi::class.java)

    // Simple in-memory cache. Details omitted for brevity.
    //private val photosCache: UserCache? = null

    // similar to Google's guide https://developer.android.com/jetpack/docs/guide
    fun getPhotos(albumId: Int): MutableLiveData<List<Photo>> {
        /*
        val cached = photosCache!!.get(albumId)
        if (cached != null) {
            return cached
        }
        */
        val data = MutableLiveData<List<Photo>>()

        launch(UI) {

            //userCache!!.put(userId, data)
            try {
                val result = webservice.getPhotosByAlbum(albumId)

                val photos = result.await()
                Log.d("NJW***","photo1 ${photos[0].title})")
                data.value = photos
            } catch (httpException: HttpException) {
                Log.e("NJW-MV***", "httpException ${httpException.code()}; ${httpException.message()}")
                // do some appropriate error stuff.
            } catch (e: Exception) {
                Log.e("NJW-MV***", "exception ${e.localizedMessage}")
                // do some appropriate error stuff.        }
            }
        }
        return data

    }
}
