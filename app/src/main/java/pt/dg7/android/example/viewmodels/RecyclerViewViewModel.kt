package pt.dg7.android.example.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.models.Image
import pt.dg7.android.example.repositories.DaggerRepositoryComponent
import pt.dg7.android.example.repositories.Repository
import javax.inject.Inject

class RecyclerViewViewModel : BaseViewModel() {
    // Companion object for holding static data
    companion object {
        const val TAG = "RecyclerViewViewModel"
    }

    // Repository instantiated and injected by Dagger
    @set:Inject
    lateinit var repo: Repository

    private val _comments = MutableLiveData<List<Comment>>()
    // Comments public getter
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _avatars = MutableLiveData<List<Image>>()
    // Images public getter
    val avatars: LiveData<List<Image>>
        get() = _avatars

    init {
        // Request field injection by Dagger
        DaggerRepositoryComponent.create().inject(this)

        // Initialize comments with empty list
        _comments.value = listOf()

        // Retrieve data
        getComments()
        getAvatars()
    }

    private fun getComments() {
        val disposable = repo.getComments()
            .subscribe({
                _comments.value = it
            }, {
                Log.w(TAG, it.message)
            })

        addDisposable(disposable)
    }

    private fun getAvatars() {
        val disposable = repo.getAvatars(30)
            .subscribe({
                _avatars.value = it
            }, {
                Log.w(TAG, it.message)
            })

        addDisposable(disposable)
    }
}