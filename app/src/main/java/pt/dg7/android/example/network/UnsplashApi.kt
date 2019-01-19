package pt.dg7.android.example.network

import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplashApi {

    /**
     * Get the given number of random photos (max: 30)
     * @ref https://unsplash.com/documentation#get-a-random-photo
     */
    @GET("/photos/random")
    fun getRandomPhoto(@Query("count") count: Int = 1)
}