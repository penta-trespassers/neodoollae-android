package com.pentatrespassers.neodoollae.network.api.v1

import com.pentatrespassers.neodoollae.dto.FriendRequest
import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.network.body.FriendRequestBody
import com.pentatrespassers.neodoollae.network.body.MessageBody
import com.pentatrespassers.neodoollae.network.body.RegisterBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FriendApi {
    @POST("/v1/auth/kakao/register/")
    fun kakaoRegister(
        @Body
        registerBody: RegisterBody
    ): Call<Token>

    @GET("/v1/friends/")
    fun getAllFriends(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<List<ArrayList<User>>>

    @GET("/v1/friends/requests/pending/")
    fun getAllFriendRequests(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<FriendRequest>>

    @POST("/v1/friends/requests/")
    fun sendFriendRequest(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Body
        friendRequestBody: FriendRequestBody
    ): Call<MessageBody>

    @POST("/v1/friends/approve/")
    fun approveFriendRequest(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Body
        hashMap: HashMap<String, @JvmSuppressWildcards Any?>
    ): Call<Void>
}