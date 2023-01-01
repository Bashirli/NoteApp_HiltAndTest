package com.bashirli.nextlvlart.view.retrofit

import com.bashirli.nextlvlart.view.model.ImageResponse
import com.bashirli.nextlvlart.view.util.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") string:String,
        @Query("key") api:String=API_KEY
    ) : Response<ImageResponse>

}