package com.example.myapplication.ui.theme

import retrofit2.http.GET
import retrofit2.http.Query



data class Images(
    val original: Original
)

data class Original(
    val url: String,
    val width: Int,
    val height: Int
)

data class ImageResponse(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)
data class GifData(
    val id: String,
    val url: String,
    val images: Images
)

data class GifResponse(
    val data: List<GifData>
)

interface ApiService {
    @GET("trending")
    suspend fun getImages(
        @Query("limit") limit: Int = 10,
        @Query("rating") rating: String = "g"
    ): GifResponse
}









/*

import retrofit2.http.GET
import retrofit2.http.Query

data class ImageResponse(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
    //val breeds: List<Breed> = emptyList()
)





*/


/*data class Breed(
    val id: String,
    val name: String,
    val temper: String
)*/

/*interface ApiService {
    @GET("v1/images/search")
    suspend fun getImages(@Query("limit") limit: Int = 10): List<ImageResponse>
}*/







/*interface ApiService {
    @GET("random")
    suspend fun getImages(
        @Query("limit") limit: Int = 10,
        @Query("tag") tag: String = "",
        @Query("rating") rating: String = "g"
    ): List<ImageResponse>
}*/







// Define the data class for the response
/*data class RandomGifResponse(
    val data: GifData


data class GifData(
    val id: String,
    val url: String
)*/
















