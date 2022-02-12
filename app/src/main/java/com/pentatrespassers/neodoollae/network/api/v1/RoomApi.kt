package com.pentatrespassers.neodoollae.network.api.v1

import com.pentatrespassers.neodoollae.dto.Room
import retrofit2.Call
import retrofit2.http.*

interface RoomApi {
    @GET("/v1/rooms/")
    fun getRooms(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("userId")
        userId: Int?
    ): Call<ArrayList<Room>>

    @POST("/v1/rooms/")
    fun createRoom(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Body
        room: Room
    ): Call<Void>

    @GET("/v1/rooms/fav")
    fun getMyFavoriteRooms(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<Room>>
}