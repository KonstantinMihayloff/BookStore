package konstmih.app.bookstore.repository

import android.content.Context
import android.util.Log
import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository () {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncBooksNow(){
        Timber.i("Synchronizing books now")
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .beginUniqueWork("syncBooksNow", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }

    fun scheduleBooksSync(){

        Timber.i("Synchronizing books every 12 hours")
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .enqueueUniquePeriodicWork("SyncBooksScheduled",
                                        ExistingPeriodicWorkPolicy.KEEP,
                                        work)

    }

}