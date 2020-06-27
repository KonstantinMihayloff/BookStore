package konstmih.app.bookstore.bookslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import konstmih.app.bookstore.Book
import konstmih.app.bookstore.R
import kotlinx.android.synthetic.main.item_book.view.*

class BooksListAdapter(private val books: List<Book>,
                        private val listener: BooksListAdapterListener?)
    : RecyclerView.Adapter<BooksListAdapter.ViewHolder>(), View.OnClickListener {

    // Listener Interface
    interface BooksListAdapterListener {
        fun onBookSelected(book: Book)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get from layouts
        val cardView = itemView.findViewById<CardView>(R.id.cardView)!!
        val bookCover = itemView.findViewById<ImageView>(R.id.bookCover)!!
        val bookTitle = itemView.findViewById<TextView>(R.id.bookTitle)!!
        val bookAuthor = itemView.findViewById<TextView>(R.id.bookAuthor)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return ViewHolder(viewItem)
    }

    // Update while scroll
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        with(holder) {
            cardView.setOnClickListener(this@BooksListAdapter)
            cardView.tag = book
            bookTitle.text = book.title
            bookAuthor.text = book.author

            // Get image from pictureUrl
            Picasso.get()
                .load(book.pictureUrl)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(bookCover)
        }
    }

    // Get Item Count
    override fun getItemCount() = books.size

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cardView -> listener?.onBookSelected(v?.tag as Book)
        }
    }
}