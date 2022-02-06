package com.pentatrespassers.neodoollae.network.api

import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.network.api.v1.FriendApi
import com.pentatrespassers.neodoollae.network.api.v1.UserApi
import com.pentatrespassers.neodoollae.network.body.KakaoLoginBody
import retrofit2.Call
import retrofit2.http.*


// 여기서 구현
interface RetrofitApi: UserApi, FriendApi {

    @POST("/v1/auth/kakao/login/")
    fun kakaoLogin(
        @Body
        kakaoLoginBody: KakaoLoginBody
    ): Call<Token>

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