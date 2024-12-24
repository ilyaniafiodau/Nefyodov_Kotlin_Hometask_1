

package com.example.myapplication.ui.theme

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor


object RetrofitClient {
    private const val BASE_URL = "https://api.giphy.com/v1/gifs/"
    private const val API_KEY = "AdmgypyfXKVAk2PwRsTMEUQujyUmdk02"

    private val interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val urlWithApiKey = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        chain.proceed(requestWithApiKey)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

// Helper function to convert GifResponse to List<ImageResponse>
suspend fun getImagesAsList(
    apiService: ApiService,
    limit: Int = 25,
    //tag: String = "",
    rating: String = "g"
): List<ImageResponse> {
    val gifResponse = apiService.getImages(limit, /*tag,*/ rating)
    return gifResponse.data.map { gifData ->
        ImageResponse(
            id = gifData.id,
            url = gifData.images.original.url,
            width = gifData.images.original.width,
            height = gifData.images.original.height
        )
    }
}