package com.pentatrespassers.neodoollae.network

import com.pentatrespassers.neodoollae.dto.*
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Param
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.network.api.RetrofitApi
import com.pentatrespassers.neodoollae.network.body.FavoriteBody
import com.pentatrespassers.neodoollae.network.body.KakaoLoginBody
import com.pentatrespassers.neodoollae.network.body.RegisterBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val instance = Retrofit.Builder().baseUrl(Param.DATABASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApi::class.java)

    fun kakaoLogin(
        kakaoToken: String?,
        fcmToken: String,
        onUnsuccessful: ((Call<Token?>, Response<Token?>) -> Unit)? = null,
        onSuccessful: (Call<Token?>, Response<Token?>) -> Unit
    ) =
        instance.kakaoLogin(KakaoLoginBody(kakaoToken, fcmToken))
            .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun kakaoRegister(
        kakaoAccessToken: String?,
        nickname: String?,
        onUnsuccessful: ((Call<Token?>, Response<Token?>) -> Unit)? = null,
        onSuccessful: (Call<Token?>, Response<Token?>) -> Unit
    ) = instance.kakaoRegister(
        RegisterBody(kakaoAccessToken, nickname)
    ).enqueue(defaultCallback(onUnsuccessful, onSuccessful))


    fun getUser(
        friendCode: String,
        onUnsuccessful: ((Call<User?>, Response<User?>) -> Unit)? = null,
        onSuccessful: (Call<User?>, Response<User?>) -> Unit
    ) = instance.getUser(Authentication.bearerAccessToken, friendCode)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getUserById(
        userId: Int,
        onUnsuccessful: ((Call<User?>, Response<User?>) -> Unit)? = null,
        onSuccessful: (Call<User?>, Response<User?>) -> Unit
    ) = instance.getUserById(Authentication.bearerAccessToken, userId)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getMyInfo(
        onUnsuccessful: ((Call<User>, Response<User>) -> Unit)? = null,
        onSuccessful: (Call<User>, Response<User>) -> Unit
    ) = instance.getMyInfo(Authentication.bearerAccessToken)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun setFavorite(
        favoriteBody: FavoriteBody,
        onUnsuccessful: ((Call<Void>, Response<Void>) -> Unit)? = null,
        onSuccessful: (Call<Void>, Response<Void>) -> Unit
    ) = instance.setFavorite(Authentication.bearerAccessToken, favoriteBody)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getAllFriends(
        onUnsuccessful: ((Call<List<ArrayList<User>>>, Response<List<ArrayList<User>>>) -> Unit)? = null,
        onSuccessful: (Call<List<ArrayList<User>>>, Response<List<ArrayList<User>>>) -> Unit
    ) = instance.getAllFriends(Authentication.bearerAccessToken)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getAllFriendRequests(
        onUnsuccessful: ((Call<ArrayList<FriendRequest>>, Response<ArrayList<FriendRequest>>) -> Unit)? = null,
        onSuccessful: (Call<ArrayList<FriendRequest>>, Response<ArrayList<FriendRequest>>) -> Unit
    ) = instance.getAllFriendRequests(Authentication.bearerAccessToken)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getRooms(
        userId: Int,
        onUnsuccessful: ((Call<ArrayList<Room>>, Response<ArrayList<Room>>) -> Unit)? = null,
        onSuccessful: (Call<ArrayList<Room>>, Response<ArrayList<Room>>) -> Unit
    ) = instance.getRooms(Authentication.bearerAccessToken, userId)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getMyFavoriteRooms(
        onUnsuccessful: ((Call<ArrayList<Room>>, Response<ArrayList<Room>>) -> Unit)? = null,
        onSuccessful: (Call<ArrayList<Room>>, Response<ArrayList<Room>>) -> Unit
    ) = instance.getMyFavoriteRooms(Authentication.bearerAccessToken)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))


    fun getAllMyReservations(
        onUnsuccessful: ((Call<ArrayList<Reservation>>, Response<ArrayList<Reservation>>) -> Unit)? = null,
        onSuccessful: (Call<ArrayList<Reservation>>, Response<ArrayList<Reservation>>) -> Unit
    ) = instance.getAllMyReservations(Authentication.bearerAccessToken)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    fun getMySchedules(
        term: Int? = null,
        onUnsuccessful: ((Call<Map<String, ArrayList<Reservation>>>, Response<Map<String, ArrayList<Reservation>>>) -> Unit)? = null,
        onSuccessful: (Call<Map<String, ArrayList<Reservation>>>, Response<Map<String, ArrayList<Reservation>>>) -> Unit
    ) = instance.getMySchedules(Authentication.bearerAccessToken, term)
        .enqueue(defaultCallback(onUnsuccessful, onSuccessful))

    private fun <T> defaultCallback(
        onUnsuccessful: ((Call<T>, Response<T>) -> Unit)?,
        onSuccessful: (Call<T>, Response<T>) -> Unit
    ) = object : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onSuccessful(call, response)
            } else {
                onUnsuccessful?.run {
                    this(call, response)
                } ?: Util.j("실패: $response")
            }
        }
        override fun onFailure(call: Call<T>, t: Throwable) {
            Util.j("Fail: ${t.message}")
        }

    }

//
//    private fun <T> enqueue(
//        onUnsuccessful: ((Call<T>, Response<T>) -> Unit)? = null,
//        onSuccessful: (Call<T>, Response<T>) -> Unit
//    ) {
//        if (onUnsuccessful == null) {
//            defaultCallback(onSuccessful = onSuccessful)
//        } else {
//            defaultCallback(onUnsuccessful, onSuccessful)
//        }
//    }

}