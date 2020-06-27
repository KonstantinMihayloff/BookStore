package konstmih.app.bookstore.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookDetailViewModelFactory(private val bookId: Int)
    : ViewModelProvider.NewInstanceFactory(){ // This class will be a ViewModel Factory

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookDetailViewModel(bookId) as T
    }
}