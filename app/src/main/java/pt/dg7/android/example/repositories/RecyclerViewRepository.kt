package pt.dg7.android.example.repositories

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.models.Image
import pt.dg7.android.example.network.ApiClient
import pt.dg7.android.example.network.PlaceholderApi
import pt.dg7.android.example.network.UnsplashApi

object RecyclerViewRepository {
    private val placeholderApi: PlaceholderApi = ApiClient.getPlaceholderClient().create(PlaceholderApi::class.java)
    private val unsplashApi: UnsplashApi = ApiClient.getUnsplashClient().create(UnsplashApi::class.java)

    fun getComments(): Observable<List<Comment>> {
        return placeholderApi.getComments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAvatars(count: Int): Observable<List<Image>> {
        if (count > 30) {
            throw IllegalArgumentException("The value of 'count' can't be greater that 30.")
        }

        return unsplashApi.getRandomPhotos(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}