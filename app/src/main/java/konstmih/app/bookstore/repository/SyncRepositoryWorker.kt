package konstmih.app.bookstore.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import konstmih.app.bookstore.App
import timber.log.Timber

// Worker for books sync
class SyncRepositoryWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        Timber.i("Synchronizing books")

        // FakebookApi
        val bookApi = FakeBookApi()

        // Dao
        val bookDao = App.db.bookDao()

        // Insert books from FakebookApi
        // No need to use insertBooks in a separate thread as we are in a worker
        bookDao.insetBooks(bookApi.loadBooks())

        // No errors to manage
        return Result.success()
    }
}