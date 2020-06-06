package com.hamro.teenpatigame.datas

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class RealTimeDataBaseResponse (
    var isGameOn : Boolean,
    var playersList : List<Player>
)