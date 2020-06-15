package konstmih.app.bookstore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import konstmih.app.bookstore.Book

const val DATABASE_NAME = "book_store"

// Database definition
@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}