package com.hamro.teenpatigame.datas

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
 class CardsInHand {
	var card1Number : Int?=null
	var card1Type : Int?=null
	var card2Number : Int?=null
	var card2Type : Int?=null
	var card3Number : Int?=null
	var card3Type : Int?=null

	constructor()
	constructor(
		card1Number: Int?,
		card1Type: Int?,
		card2Number: Int?,
		card2Type: Int?,
		card3Number: Int?,
		card3Type: Int?
	) {
		this.card1Number = card1Number
		this.card1Type = card1Type
		this.card2Number = card2Number
		this.card2Type = card2Type
		this.card3Number = card3Number
		this.card3Type = card3Type
	}

}