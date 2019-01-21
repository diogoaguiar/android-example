package pt.dg7.android.example.network

import io.reactivex.Observable
import pt.dg7.android.example.models.Image
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ref https://unsplash.com/documentation
 */
interface UnsplashApi {

    /**
     * Get the given number of random photos (max: 30)
     * @ref https://unsplash.com/documentation#get-a-random-photo
     */
    @GET("/photos/random")
    fun getRandomPhotos(@Query("count") count: Int = 1): Observable<List<Image>>

    @GET("/photos/random")
    fun getRandomPhoto(): Observable<Image>
}