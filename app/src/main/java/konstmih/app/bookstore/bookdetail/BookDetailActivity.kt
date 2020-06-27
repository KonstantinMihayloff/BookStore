package konstmih.app.bookstore.bookdetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
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
        setSupportActionBar(toolbar)

        // Get intent
        val bookId = intent.getIntExtra(EXTRA_BOOK_ID, 1)
        Timber.d("Book id = $bookId")

        // ViewModel + Factory
        // A factory is used here so that after a screen rotation, the same view model will be used
        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)

    }

}
