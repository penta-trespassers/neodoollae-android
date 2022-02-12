package com.pentatrespassers.neodoollae.network.api.v1

import com.pentatrespassers.neodoollae.dto.Reservation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ReservationApi {
    @GET("/v1/reservations/")
    fun getAllMyReservations(
        @Header("Authorization")
        bearerAccessToken: String?
    ): Call<ArrayList<Reservation>>

    @GET("/v1/reservations/myroom/")
    fun getAllMyRoomReservations(
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