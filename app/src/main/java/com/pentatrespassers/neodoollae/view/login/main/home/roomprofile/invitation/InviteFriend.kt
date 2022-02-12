package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation

import com.pentatrespassers.neodoollae.dto.User

class InviteFriend {
    var user : User = User()
    var isInvite : Boolean = false

    constructor(user : User, isInvite : Boolean){
        this.user = user
        this.isInvite =isInvite
    }
}