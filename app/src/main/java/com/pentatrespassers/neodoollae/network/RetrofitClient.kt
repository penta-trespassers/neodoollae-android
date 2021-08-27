package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.dto.body.RegisterBody
import com.pentatrespassers.neodoollae.lib.Param
import com.pentatrespassers.neodoollae.lib.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient : RetrofitApi {
    private val instance by lazy {
        init()
    }

    private fun init(): RetrofitApi = Retrofit.Builder().baseUrl(Param.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApi::class.java)

    override fun kakaoLogin(token: Token): Call<Token> = instance.kakaoLogin(token)
    override fun kakaoRegister(registerBody: RegisterBody): Call<Token> =
        instance.kakaoRegister(registerBody)


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