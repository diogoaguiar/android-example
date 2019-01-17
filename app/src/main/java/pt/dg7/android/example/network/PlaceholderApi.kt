package pt.dg7.android.example.network

import io.reactivex.Observable
import pt.dg7.android.example.models.Comment
import retrofit2.http.*

interface PlaceholderApi {
    @GET("/comments")
    fun getComments(): Observable<List<Comment>>

    @GET("/comments/{id}")
    fun getComment(@Path("id") id: Int): Observable<Comment>

    @GET("/comments")
    fun getPostComments(@Query("postId") post: Int): Observable<List<Comment>>

    @POST("/comments")
    fun addComment(@Body request: Comment): Observable<List<Comment>>

    @PUT("/comments/{id}")
    fun updateComment(@Path("id") id: Int,
                      @Body request: Comment): Observable<Comment>

    @DELETE("/comments")
    fun deleteComment(@Path("id") id: Int): Observable<Nothing>
}