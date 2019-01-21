package pt.dg7.android.example.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.models.Image
import pt.dg7.android.example.repositories.RecyclerViewRepository

class RecyclerViewViewModel : BaseViewModel() {
    // Companion object for holding static data
    companion object {
        const val TAG = "RecyclerViewViewModel"
    }

    private val _comments = MutableLiveData<List<Comment>>()
    // Comments public getter
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _avatars = MutableLiveData<List<Image>>()
    // Images public getter
    val avatars: LiveData<List<Image>>
        get() = _avatars

    init {
        _comments.value = listOf()

        getComments()
        getAvatars()
    }

    fun getComments() {
        val disposable = RecyclerViewRepository.getComments()
            .subscribe({
                _comments.value = it
            }, {
                Log.w(TAG, it.message)
            })

        addDisposable(disposable)
    }

    fun getAvatars() {
        val disposable = RecyclerViewRepository.getAvatars(30)
            .subscribe({
                _avatars.value = it
            }, {
                Log.w(TAG, it.message)
            })

        addDisposable(disposable)
    }
}