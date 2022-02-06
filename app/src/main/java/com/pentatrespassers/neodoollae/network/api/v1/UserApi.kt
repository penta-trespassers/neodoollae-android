package com.pentatrespassers.neodoollae.network.api.v1

import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.network.body.FavoriteBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @GET("/v1/users/")
    fun getUser(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Query("friendCode")
        friendCode: String
    ): Call<User?>

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

    @POST("/v1/users/fav/")
    fun setFavorite(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Body
        favoriteBody: FavoriteBody
    ): Call<Void>

    @GET("/v1/users/{id}")
    fun getUserById(
        @Header("Authorization")
        bearerAccessToken: String?,
        @Path("id")
        userId: Int?
    ): Call<User>
}