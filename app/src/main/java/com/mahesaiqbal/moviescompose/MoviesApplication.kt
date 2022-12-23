package com.mahesaiqbal.moviescompose

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mahesaiqbal.moviescompose.di.dataSourceModule
import com.mahesaiqbal.moviescompose.di.databaseModule
import com.mahesaiqbal.moviescompose.di.networkModule
import com.mahesaiqbal.moviescompose.di.repositoryModule
import com.mahesaiqbal.moviescompose.di.useCaseModule
import com.mahesaiqbal.moviescompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MoviesApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}