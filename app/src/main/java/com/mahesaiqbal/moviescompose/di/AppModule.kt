package com.mahesaiqbal.moviescompose.di

import androidx.room.Room
import com.mahesaiqbal.moviescompose.BuildConfig
import com.mahesaiqbal.moviescompose.data.MoviesRepository
import com.mahesaiqbal.moviescompose.data.source.local.LocalDataSource
import com.mahesaiqbal.moviescompose.data.source.local.room.MoviesDatabase
import com.mahesaiqbal.moviescompose.data.source.remote.RemoteDataSource
import com.mahesaiqbal.moviescompose.data.source.remote.network.ApiService
import com.mahesaiqbal.moviescompose.domain.repository.IMoviesRepository
import com.mahesaiqbal.moviescompose.domain.usecase.MoviesInteractor
import com.mahesaiqbal.moviescompose.domain.usecase.MoviesUseCase
import com.mahesaiqbal.moviescompose.ui.viewmodel.MoviesViewModel
import com.mahesaiqbal.moviescompose.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single { get<MoviesDatabase>().moviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java,
            "MoviesCompose.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val dataSourceModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
}

val repositoryModule = module {
    single<IMoviesRepository> { MoviesRepository(get(), get(), get()) }
}

val useCaseModule = module {
    single<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
//    viewModel { MoviesViewModel(get()) }
    viewModelOf(::MoviesViewModel)
}