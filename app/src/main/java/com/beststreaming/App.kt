package com.beststreaming

import android.app.Application
import com.beststreaming.model.data.ApiHelper
import com.beststreaming.model.data.ApiHelperImpl
import com.beststreaming.model.data.ApiService
import com.beststreaming.model.repository.RemoteRepository
import com.beststreaming.viewmodels.MovieViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            //Starting modules
            modules(
                listOf(appModule, repoModule, viewModelModule)
            )
        }
    }
}

val appModule = module {
    //Inject Singleton in module appModule
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), "https://gist.githubusercontent.com/") }
    single { provideApiService(get()) }

    //Inject ApiHelperImpl when calling ApiHelper
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

val repoModule = module {
    single {
        RemoteRepository(get())
    }
}

/*
@return Proveedor Retrofit
 */
fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        ))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

/*
@return OkHttpClient
 */
private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

val viewModelModule = module {
    viewModel{
        MovieViewModel(get())
    }
}