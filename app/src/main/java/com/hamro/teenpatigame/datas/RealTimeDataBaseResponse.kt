package com.hamro.teenpatigame.datas
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
class RealTimeDataBaseResponse {
    var gameOn: Boolean?=false
    var players: Players? =null
    var winners: Winners? =null
    var activePlayers: Int? =0
    var currentTurn: Int? =0

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }
    constructor(gameOn: Boolean?, players: Players?, winners: Winners?,activePlayers: Int?,currentTurn:Int?) {
        this.gameOn = gameOn
        this.players = players
        this.winners = winners
        this.activePlayers = activePlayers
        this.currentTurn = currentTurn
    }

}