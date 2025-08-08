package ru.ymv.yvideolite.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Dagger Hilt module for providing an ExoPlayer [Player] instance.
 */
@Module
@InstallIn(ViewModelComponent::class)
object ExoPlayerModule {

    /**
     * Provides an ExoPlayer [Player] instance.
     *
     * The provided player is scoped to the ViewModel lifecycle and configured to start playing
     * automatically when ready.
     *
     * @param app The application context.
     * @return An ExoPlayer [Player] instance.
     */
    @Provides
    @ViewModelScoped
    fun provideExoPlayer(app: Application): Player {
        return ExoPlayer.Builder(app)
            .build().apply { playWhenReady = true }
    }
}