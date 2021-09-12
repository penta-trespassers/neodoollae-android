package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.FriendRequest
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.dto.body.RegisterBody
import retrofit2.Call
import retrofit2.http.*


// 여기서 구현
interface RetrofitApi {

    @POST("auth/kakao/login/")
    fun kakaoLogin(
        @Body
        token: Token
    ): Call<Token>

    @POST("auth/kakao/register/")
    fun kakaoRegister(
        @Body
        registerBody: RegisterBody
    ): Call<Token>

    @GET("users/my/")
    fun getMyInfo(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<User>

    @GET("friends/")
    fun getAllFriends(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<List<User>>

    @GET("friends/requests/pending/")
    fun getAllFriendRequests(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<List<FriendRequest>>

    @GET("rooms/")
    fun getRooms(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("userId")
        userId: Int?
    ): Call<ArrayList<Room>>

}