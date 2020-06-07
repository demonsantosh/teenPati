package com.hamro.teenpatigame.datas

import com.hamro.teenpatigame.Card

class Deck {
    private val cardDeck: Array<Card?>
    private var top_card = 0
    fun shuffle() {
        for (i in cardDeck.size - 1 downTo 1) {
            val rand = (Math.random() * (i + 1)).toInt()
            val temp = cardDeck[i]
            cardDeck[i] = cardDeck[rand]
            cardDeck[rand] = temp
        }
    }

    fun dealCard(): Card? {
        return cardDeck[top_card++]
    }

    init {
        cardDeck = arrayOfNulls(52)
        var count = 0
        for (i in 1..4) for (j in 2..14) cardDeck[count++] = Card(i, j)
        shuffle()
    }
}