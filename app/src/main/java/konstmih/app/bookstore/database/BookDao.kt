package konstmih.app.bookstore.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import konstmih.app.bookstore.Book

// Dao file for Book data class
@Dao
interface BookDao {

    // Get all books from table "book"
    @Query("SELECT * FROM book")
    fun getAllBooks(): LiveData<List<Book>>

    // Get a particular book
    @Query("SELECT * from book WHERE id = :id")
    fun getBookById(id: Int) : LiveData<Book>

    // Insert multiple books
    @Insert
    fun insetBooks(books: List<Book>)
}