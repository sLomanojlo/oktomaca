package rs.sloman.oktomaca

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/** Base Application that serves to initialize Dagger and Timber. */
@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}