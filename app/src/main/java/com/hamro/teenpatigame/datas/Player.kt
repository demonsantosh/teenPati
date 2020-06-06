package com.hamro.teenpatigame.datas

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Player (
	var id : Int,
	 var playerName : String,
	 var money : Int,
	 var isPlaying : Boolean,
	 var isOut : Boolean,
	 var cardList : MutableList<Card>
){
	fun addMoney(amt: Int) {
		money += amt
	}
	fun deductMoney(amt: Int) {
		money -= amt
	}
	fun addCard(card: Card?) {
		card?.let { cardList.add(it) }
	}
	fun sortCards() {
		val tempList = mutableListOf<Card>()
		while (cardList.size != 0) {
			var best: Card = cardList.get(0)
			for (i in 1 until cardList.size) if (cardList.get(i).cardNumber > best.cardNumber) best =
				cardList.get(i)
			tempList.add(best)
			cardList.remove(best)
		}
		cardList = tempList
	}
}