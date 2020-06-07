package com.hamro.teenpatigame.datas
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Players{
	var player1: Player1? =null
	var player2: Player2? =null
	var player3: Player3? =null
	var player4: Player4? =null
	var player5: Player5? =null
	constructor()
	constructor(
		player1: Player1?,
		player2: Player2?,
		player3: Player3?,
		player4: Player4?,
		player5: Player5?
	) {
		this.player1 = player1
		this.player2 = player2
		this.player3 = player3
		this.player4 = player4
		this.player5 = player5
	}


}