package konstmih.app.bookstore.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// A factory is used to be able to use the same ViewModel after a screen rotation
// It allows also to pass bookId as a parameter of the ViewModel
class BookDetailViewModelFactory(private val bookId: Int)
    : ViewModelProvider.NewInstanceFactory(){ // This class will be a ViewModel Factory

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookDetailViewModel(bookId) as T
    }
}