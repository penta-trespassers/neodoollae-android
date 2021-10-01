package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.*
import com.pentatrespassers.neodoollae.dto.body.RegisterBody
import retrofit2.Call
import retrofit2.http.*


// 여기서 구현
interface RetrofitApi {

    @POST("/v1/auth/kakao/login/")
    fun kakaoLogin(
        @Body
        token: Token
    ): Call<Token>

    @POST("/v1/auth/kakao/register/")
    fun kakaoRegister(
        @Body
        registerBody: RegisterBody
    ): Call<Token>

    @GET("/v1/users/my/")
    fun getMyInfo(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<User>

    @GET("/v1/friends/")
    fun getAllFriends(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<User>>

    @GET("/v1/friends/requests/pending/")
    fun getAllFriendRequests(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<List<FriendRequest>>

    @GET("/v1/rooms/")
    fun getRooms(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("userId")
        userId: Int?
    ): Call<ArrayList<Room>>

    @GET("/v1/reservations/")
    fun getAllMyReservations(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<Reservation>>

}