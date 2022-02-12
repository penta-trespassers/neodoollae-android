package com.pentatrespassers.neodoollae.network.body.searchplace

import com.google.gson.annotations.SerializedName

data class DocumentBody(
    @SerializedName("address_name")
    val addressName: String,
    @SerializedName("road_address_name")
    val roadAddressName: String,
    @SerializedName("y")
    val latitude: Double,
    @SerializedName("x")
    val longitude: Double
)
