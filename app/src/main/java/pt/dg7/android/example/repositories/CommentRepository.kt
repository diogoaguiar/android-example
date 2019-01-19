package pt.dg7.android.example.repositories

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.network.ApiClient
import pt.dg7.android.example.network.PlaceholderApi

object CommentRepository {
    private val api: PlaceholderApi = ApiClient.getPlaceholderClient().create(PlaceholderApi::class.java)

    fun getComments(): Observable<List<Comment>> {
        return api.getComments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}