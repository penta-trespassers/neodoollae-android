package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.FriendRequest
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.dto.body.RegisterBody
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Param
import com.pentatrespassers.neodoollae.lib.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val instance = Retrofit.Builder().baseUrl(Param.DATABASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApi::class.java)

    fun kakaoLogin(kakaoAccessToken: String?) =
        instance.kakaoLogin(Token(access = kakaoAccessToken))

    fun kakaoRegister(kakaoAccessToken: String?, nickname: String?) = instance.kakaoRegister(
        RegisterBody(kakaoAccessToken, nickname)
    )

    fun getMyInfo() = instance.getMyInfo(Authentication.bearerAccessToken)
    fun getAllFriends() = instance.getAllFriends(Authentication.bearerAccessToken)
    fun getAllFriendRequests(
        onUnsuccessful: ((Call<List<FriendRequest>>, Response<List<FriendRequest>>) -> Unit)? = null,
        onSuccessful: (Call<List<FriendRequest>>, Response<List<FriendRequest>>) -> Unit
    ) = instance.getAllFriendRequests(Authentication.bearerAccessToken).enqueue(
        if (onUnsuccessful == null) {
            defaultCallback (onSuccessful = onSuccessful)
        } else {
            defaultCallback(onUnsuccessful, onSuccessful)
        }
    )
    fun getRoom(
        userId: Int,
        onUnsuccessful: ((Call<List<Room>>, Response<List<Room>>) -> Unit)? = null,
        onSuccessful: (Call<List<Room>>, Response<List<Room>>) -> Unit
    ) = instance.getRooms(Authentication.bearerAccessToken, userId).enqueue(
        if (onUnsuccessful == null) {
            defaultCallback (onSuccessful = onSuccessful)
        } else {
            defaultCallback(onUnsuccessful, onSuccessful)
        }
    )

    fun <T> defaultCallback(
        onUnsuccessful: (Call<T>, Response<T>) -> Unit = { _, response ->
            Util.j("실패: $response")
        },
        onSuccessful: (Call<T>, Response<T>) -> Unit
    ) = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onSuccessful(call, response)
            } else {
                onUnsuccessful(call, response)
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Util.j("Fail: ${t.message}")
        }

    }


}