package com.pentatrespassers.neodoollae.network.body

import com.pentatrespassers.neodoollae.network.body.searchplace.DocumentBody

data class SearchPlaceBody(
    val documents: ArrayList<DocumentBody>
)
