package pt.dg7.android.example.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides the Api instances used by Dagger in the injection processes
 */
@Suppress("unused")
@Module
class ApiModule {

    @Singleton
    @Provides
    fun providesPlaceholderApi(): PlaceholderApi {
        return createPlaceholderClient().create(PlaceholderApi::class.java)
    }

    @Singleton
    @Provides
    fun providesUnsplashApi(): UnsplashApi {
        return createUnsplashClient().create(UnsplashApi::class.java)
    }

    private fun createPlaceholderClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createUnsplashClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor {
                // Add custom authorization header
                val request = it.request()
                    .newBuilder()
                    .header(
                        "Authorization",
                        "Client-ID b93e843ac18f51fa79e7203f4f8c6124c98b4ae1ff60d511fb028a92c6a3233c"
                    ) // Replaces "Authorization" header
                    // .addHeader(
                    //     "Authorization",
                    //     "Client-ID b93e843ac18f51fa79e7203f4f8c6124c98b4ae1ff60d511fb028a92c6a3233c"
                    // ) // Appends value to the "Authorization" header
                    .build()

                it.proceed(request)
            }
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}