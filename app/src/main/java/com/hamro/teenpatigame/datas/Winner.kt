package com.hamro.teenpatigame.datas

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
 class Winners {
    var firstId : Int?=1
    var fourthId : Int?=4
    var secondId : Int?=2
    var thirdId : Int?=3
    constructor()
    constructor(firstId: Int?, fourthId: Int?, secondId: Int?, thirdId: Int?) {
        this.firstId = firstId
        this.fourthId = fourthId
        this.secondId = secondId
        this.thirdId = thirdId
    }
}

