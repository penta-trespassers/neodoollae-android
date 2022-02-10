package com.pentatrespassers.neodoollae.lib

import splitties.preferences.Preferences

object UserPreferences: Preferences("userState") {
    var accessToken by stringOrNullPref("accessToken", null)
}