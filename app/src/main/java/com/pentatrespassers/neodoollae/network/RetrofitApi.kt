package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.dto.body.RegisterBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


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

    @GET("friends/")
    fun getAllFriends(
        @Header("Authorization")
        accessToken: String?
    ): Call<List<User>>

}