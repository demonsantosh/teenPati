package com.hamro.teenpatigame.datas

import com.google.firebase.database.IgnoreExtraProperties
@IgnoreExtraProperties
class Player3 {
	var cardsInHand: CardsInHand? =null
	var id: Int?=3
	var out: Boolean ?= true
	var playing: Boolean? = false
	var money: Int ?= 0
	var playerName: String? = "Player 3"

	constructor(){
		//this is comment
	}
	constructor(
		cardsInHand: CardsInHand?,
		id: Int?,
		out: Boolean?,
		playing: Boolean?,
		money: Int?,
		playerName: String?
	) {
		this.cardsInHand = cardsInHand
		this.id = id
		this.out = out
		this.playing = playing
		this.money = money
		this.playerName = playerName
	}

}