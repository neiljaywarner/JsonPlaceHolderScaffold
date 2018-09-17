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

    val isLoading = MutableLiveData<Boolean>()

    fun getPhotos(albumId: Int): MutableLiveData<List<Photo>> {

        val data = MutableLiveData<List<Photo>>()

        // TODO: Make sure exception bubbles up so we can have error dialogs

        launch(UI) {

            try {
                isLoading.postValue(true)
                val result = webservice.getPhotosByAlbum(albumId)

                val photos = result.await()
                Log.d("NJW***","photo1 ${photos[0].title})")
                data.value = photos
            } catch (httpException: HttpException) {
                Log.e("NJW-***", "httpException ${httpException.code()}; ${httpException.message()}")
                //TODO wrap exception
                throw httpException
                // do some appropriate error stuff.
            } catch (e: Exception) {
                Log.e("NJW-***", "exception ${e.localizedMessage}")
                throw e
                // do some appropriate error stuff.        }
            } finally {
                isLoading.postValue(false)
            }
        }
        return data

    }
    // Note: re: in progress operations/error - can add another livedata that returns isLoading, isError
    // or can wrap in network bundle
    // see https://developer.android.com/jetpack/docs/guide#show-in-progress-operations
}
