package com.pentatrespassers.neodoollae.network.api

import com.pentatrespassers.neodoollae.network.body.SearchPlaceBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface KakaoApi {
    @POST("/v2/local/search/keyword.json")
    fun searchPlace(
        @Header("Authorization")
        authorization: String,
        @Query("query")
        keyword: String
    ): Call<SearchPlaceBody>
}