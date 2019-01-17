package pt.dg7.android.example.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseViewModel: ViewModel() {
    /**
     * Holds the RxJava disposables from the ViewModel
     * @ref https://proandroiddev.com/managing-disposables-in-rxjava-2-the-less-bad-version-b3ff2b0b72a2
     */
    private val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Called when the ViewModel is no longer used and will be destroyed.
     */
    override fun onCleared() {
        // Clear the ViewModel disposables
        disposables.clear()
    }

    /**
     * Adds a disposable to the disposables collection
     * Disposables generated within the ViewModel should be added to the collection
     */
    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}