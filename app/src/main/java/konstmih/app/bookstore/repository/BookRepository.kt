package konstmih.app.bookstore.repository

import android.content.Context
import android.util.Log
import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository () {

    // Constraints
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncBooksNow(){
        // Log
        Timber.i("Synchronizing books now")

        // Work
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()

        // Queue
        WorkManager.getInstance()
            .beginUniqueWork("syncBooksNow", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }

    fun scheduleBooksSync(){

        // Log
        Timber.i("Synchronizing books every 12 hours")

        // Work
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        // Queue
        WorkManager.getInstance()
            .enqueueUniquePeriodicWork("SyncBooksScheduled",
                                        ExistingPeriodicWorkPolicy.KEEP,
                                        work)

    }

}