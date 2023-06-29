package org.lotka.bp.presentation

import android.app.Application
import android.util.Log
import android.widget.Toast
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImage
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import org.lotka.bp.presentation.util.UnsplashSizingInterceptor

@HiltAndroidApp
class BaseApplication : Application(), ImageLoaderFactory {

    /**
     * Create the singleton [ImageLoader].
     * This is used by [AsyncImage] to load images in the app.
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        // [START retrieve_current_token] & Send token to server
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Push Notification", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast
            Log.d("TAG", "Fcm token : $token ")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }




}

















