package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.util.Log
import com.neiljaywarner.jsonplaceholderscaffold.db.PhotoDao
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


class PhotoRepository(private val webServiceApi: WebServiceApi, private val photosDao: PhotoDao) {
    //TODO: pass this in via constructor injection

    // TODO: Also see https://proandroiddev.com/the-missing-google-sample-of-android-architecture-components-guide-c7d6e7306b8f
    // or https://github.com/chrisbanes/tivi


    val isLoading = MutableLiveData<Boolean>()

    fun getPhotos(albumId: Int): LiveData<List<Photo>> {

        refreshPhotos(albumId)
        launch(UI) {


        }
        return photosDao.getData()

    }
    // TOD: note    https://developer.android.com/jetpack/docs/guide#show-in-progress-operations
    private fun refreshPhotos(albumId: Int) = launch(UI) {
        val hasFreshData: Boolean = photosDao.getCountFreshRows(1, 22) > 0
        //if (photosDao.hasFreshData(albumId, 10.millisAgo()))
        try {
            isLoading.postValue(true)
            val result = webServiceApi.getPhotosByAlbum(albumId)

            val photos = result.await()
            Log.d("NJW***","photo1 ${photos[0].title})")
            //photosDao.saveAll(photos)
        } catch (httpException: HttpException) {
            Log.e("NJW-***", "httpException ${httpException.code()}; ${httpException.message()}")
            //TODO wrap exception
            throw httpException
        } catch (e: Exception) {
            Log.e("NJW-***", "exception ${e.localizedMessage}")
            throw e
        } finally {
            isLoading.postValue(false)
        }
    }
    // Note: re: in progress operations/error - can add another livedata that returns isLoading, isError
    // or can wrap in network bundle
    // see https://developer.android.com/jetpack/docs/guide#show-in-progress-operations
}

fun Int.millisAgo() : Long {
    System.currentTimeMillis() - this
}


