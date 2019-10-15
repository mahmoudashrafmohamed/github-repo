package com.dev.mahmoud_ashraf.github_repo.di.modules

import android.app.Application
import androidx.room.Room
import com.dev.mahmoud_ashraf.github_repo.data.repository.local.RepoDatabase
import com.dev.mahmoud_ashraf.github_repo.data.repository.remote.GithubAPI
import com.dev.mahmoud_ashraf.github_repo.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    internal fun provideSplApi(okHttpClient: OkHttpClient): GithubAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GithubAPI::class.java)
    }

     @Singleton
    @Provides
    internal fun provideDb(app: Application): RepoDatabase {
    return Room.databaseBuilder( app,
             RepoDatabase::class.java, "github.db")
             .build()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}