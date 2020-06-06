package com.hamro.teenpatigame.datas

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Card(
	var cardNumber: Int,
	var cardType: Int
){
	var CLUBS: Int = 1 /*chidi*/
	var DIAMONDS: Int = 2 /*itta*/
	var HEARTS: Int = 3 /*pana*/
	var SPADES: Int = 4 /*bhote*/

	fun getCardTypeAsAsString(): String? {
		return when (cardType) {
			SPADES -> "S"
			CLUBS -> "C"
			DIAMONDS -> "D"
			HEARTS -> "H"
			else -> null
		}
	}
	fun getCardNumAsString(): String? {
		return when (cardNumber) {
			14 -> "A"
			2 -> "2"
			3 -> "3"
			4 -> "4"
			5 -> "5"
			6 -> "6"
			7 -> "7"
			8 -> "8"
			9 -> "9"
			10 -> "10"
			11 -> "J"
			12 -> "Q"
			13 -> "K"
			else -> null
		}
	}
	override fun toString(): String {
		return getCardTypeAsAsString() + getCardNumAsString()
	}

}