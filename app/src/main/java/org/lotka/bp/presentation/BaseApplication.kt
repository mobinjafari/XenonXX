package org.lotka.bp.presentation

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImage
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
}

















