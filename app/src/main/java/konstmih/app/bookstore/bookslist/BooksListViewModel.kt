package konstmih.app.bookstore.bookslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import konstmih.app.bookstore.App
import konstmih.app.bookstore.Book

class BooksListViewModel : ViewModel() {

    val books: LiveData<List<Book>> = App.db.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.syncBooksNow()
    }
}