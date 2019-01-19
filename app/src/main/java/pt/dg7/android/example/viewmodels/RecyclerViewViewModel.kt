package pt.dg7.android.example.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.repositories.CommentRepository

class RecyclerViewViewModel : BaseViewModel() {
    // Companion object for holding static data
    companion object {
        const val TAG = "RecyclerViewViewModel"
    }

    private val _comments = MutableLiveData<List<Comment>>()
    // Comments public getter
    val comments: LiveData<List<Comment>>
        get() = _comments

    init {
        _comments.value = listOf()

        getComments()
    }

    fun getComments() {
        val disposable = CommentRepository.getComments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _comments.value = it
            }, {
                Log.w(TAG, it.message)
            })

        addDisposable(disposable)
    }
}