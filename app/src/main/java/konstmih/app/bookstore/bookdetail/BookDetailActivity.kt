package konstmih.app.bookstore.bookdetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import konstmih.app.bookstore.Book
import konstmih.app.bookstore.R

import kotlinx.android.synthetic.main.activity_book_detail.*
import timber.log.Timber

// Activity that shows the book detail after clicking on a book
class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK_ID = "bookId"
    }

    // ViewModel
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // Get intent
        val bookId = intent.getIntExtra(EXTRA_BOOK_ID, 1)
        Timber.d("Book id = $bookId")

        // ViewModel + Factory
        // A factory is used here so that after a screen rotation, the same view model will be used
        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)
        viewModel.book.observe(this, Observer { book -> updateBook(book!!) })
    }

    private fun updateBook(book: Book) {
        // Cover
        Picasso.get()
            .load(book.pictureUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(bookCover)

        // Info
        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookSummary.text = book.summary
    }

}
