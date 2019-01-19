package pt.dg7.android.example.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var placeholderClient: Retrofit? = null
    private var unsplashClient: Retrofit? = null

    fun getPlaceholderClient(): Retrofit {
        if (placeholderClient === null) {
            placeholderClient = createPlaceholderClient()
        }

        return placeholderClient!!
    }

    fun getUnsplashClient(): Retrofit {
        if (unsplashClient === null) {
            unsplashClient = createUnsplashClient()
        }

        return unsplashClient!!
    }

    private fun createPlaceholderClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createUnsplashClient(): Retrofit {
        // Add custom authorization header
        val client = OkHttpClient.Builder()
            .addInterceptor {
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
            .build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}