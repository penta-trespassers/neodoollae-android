package com.pentatrespassers.neodoollae.network.api.v1

import com.pentatrespassers.neodoollae.dto.Room
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RoomApi {
    @GET("/v1/rooms/")
    fun getRooms(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("userId")
        userId: Int?
    ): Call<ArrayList<Room>>

    @GET("/v1/rooms/fav")
    fun getMyFavoriteRooms(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<Room>>
}