package konstmih.app.bookstore

import android.app.Application
import androidx.room.Room
import konstmih.app.bookstore.database.AppDatabase
import konstmih.app.bookstore.database.DATABASE_NAME
import konstmih.app.bookstore.repository.BookRepository
import timber.log.Timber

// Main App Class
class App : Application() {

    // Database Companion Object, so it can be used in every activity
    companion object {
        lateinit var db: AppDatabase
        lateinit var repository: BookRepository
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        // Database initialisation
        db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            DATABASE_NAME)
            .build()

        repository = BookRepository()
        repository.scheduleBooksSync()
    }
}