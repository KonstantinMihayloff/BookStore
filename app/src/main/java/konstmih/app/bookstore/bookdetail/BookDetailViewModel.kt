package konstmih.app.bookstore.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import konstmih.app.bookstore.App
import konstmih.app.bookstore.Book

// BookDetail View Model
class BookDetailViewModel(bookId: Int) : ViewModel() {
    // Mutable Live Data
    private val bookIdLiveData = MutableLiveData<Int>()

    // Transformation to LiveData
    val book: LiveData<Book> = Transformations.switchMap(bookIdLiveData) {id ->
        // Get the book from the database
        App.db.bookDao().getBookById(id)
    }

    init {
        bookIdLiveData.value = bookId
    }
}