package com.pentatrespassers.neodoollae.network.api

import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.network.api.v1.FriendApi
import com.pentatrespassers.neodoollae.network.api.v1.ReservationApi
import com.pentatrespassers.neodoollae.network.api.v1.RoomApi
import com.pentatrespassers.neodoollae.network.api.v1.UserApi
import com.pentatrespassers.neodoollae.network.body.KakaoLoginBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


// 여기서 구현
interface ServerApi: UserApi, FriendApi, RoomApi, ReservationApi {

    @POST("/v1/auth/kakao/login/")
    fun kakaoLogin(
        @Body
        kakaoLoginBody: KakaoLoginBody
    ): Call<Token>


}