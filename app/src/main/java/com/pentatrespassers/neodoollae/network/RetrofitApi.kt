package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.*
import com.pentatrespassers.neodoollae.dto.body.KakaoLoginBody
import com.pentatrespassers.neodoollae.dto.body.RegisterBody
import retrofit2.Call
import retrofit2.http.*


// 여기서 구현
interface RetrofitApi {

    @POST("/v1/auth/kakao/login/")
    fun kakaoLogin(
        @Body
        kakaoLoginBody: KakaoLoginBody
    ): Call<Token>

    @POST("/v1/auth/kakao/register/")
    fun kakaoRegister(
        @Body
        registerBody: RegisterBody
    ): Call<Token>

    @GET("/v1/users/")
    fun getUser(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("friendCode")
        friendCode: String
    ): Call<User>

    @GET("/v1/users/my/")
    fun getMyInfo(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<User>

    @POST("v1/users/fcm/")
    fun uploadFCMToken(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Body
        token: Token
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
    ): Call<ArrayList<FriendRequest>>
    @GET("/v1/users/{id}")
    fun getUserById(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Path("id")
        userId: Int?
    ): Call<User>
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

    @GET("/v1/reservations/")
    fun getAllMyReservations(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<Reservation>>

    @GET("/v1/reservations/schedules/")
    fun getMySchedules(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("term")
        term: Int? = null
    ): Call<Map<String, ArrayList<Reservation>>>

}