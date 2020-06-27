package konstmih.app.bookstore.bookslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import konstmih.app.bookstore.App
import konstmih.app.bookstore.Book
import konstmih.app.bookstore.R
import konstmih.app.bookstore.bookdetail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_books_list.*
import timber.log.Timber

class BooksListActivity : AppCompatActivity(), BooksListAdapter.BooksListAdapterListener {

    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapter
    private lateinit var books: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)

        // Books
        books = mutableListOf()
        // Adapter for RecyclerView
        booksAdapter = BooksListAdapter(books, this)

        // Recycler View Management
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BooksListActivity)
            adapter = booksAdapter
        }

        // SwipeRefresh
        swipeRefresh.setOnRefreshListener { viewModel.refreshBooks() }

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks -> updateBooks(newBooks!!) })
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("List of books $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onBookSelected(book: Book) {
        //Intent
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book.id)
        startActivity(intent)
    }
}
