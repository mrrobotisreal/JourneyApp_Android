package io.winapps.journeyapp

import android.app.Application
import android.util.Log
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp
import io.winapps.journeyapp.BuildConfig

@HiltAndroidApp
class JourneyAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (!Places.isInitialized()) {
            Log.d("BuildConfig:", BuildConfig.MAPS_API_KEY)
            Places.initialize(this, BuildConfig.MAPS_API_KEY)
        }
    }
}