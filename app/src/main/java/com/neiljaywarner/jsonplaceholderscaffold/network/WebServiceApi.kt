package com.neiljaywarner.jsonplaceholderscaffold.network


import com.neiljaywarner.jsonplaceholderscaffold.model.Photo

import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface WebServiceApi {

    // also see
    //https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/intro_understanding_username_password_oauth_flow.htm

    //@FormUrlEncoded
    //@POST("/oauth/token")
    //Call<BearerTokenResponse> getBearerToken(@Field("grant_type") String grantType);


    //@FormUrlEncoded
    //@POST("/oauth/token")
    //Deferred<BearerTokenResponse> getBearerTokenDeferred(@Field("grant_type") String grantType);

    //@POST("1/users")
    //Deferred<User> signup(@Body RegisterUserRequest registerUserRequest);

    //@POST("oauth/token")
    //Call<BearerTokenResponse> getBearerToken(@Body PasswordTokenRequest passwordTokenRequest);


    // e.g. https://jsonplaceholder.typicode.com/todos?user=2&completed=false
    // something like this
    //@GET("/todos")
    //Deferred<List<Todo>> lookupVerse(@Query("user") int user, @Query("completed) Boolean completed);

    // e.g https://jsonplaceholder.typicode.com/albums/1/photos
    // or https://jsonplaceholder.typicode.com/photos/?albumId=3
    @GET("/albums/{albumId}/photos")
    fun getPhotosByAlbum(@Path("albumId") albumId: Int): Deferred<List<Photo>>

    companion object {

        val GRANT_TYPE_CLIENT = "client_credentials"
        val GRANT_TYPE_PASSWORD = "password"
    }

}

