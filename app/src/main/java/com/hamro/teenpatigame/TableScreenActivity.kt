package com.hamro.teenpatigame

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class TableScreenActivity : AppCompatActivity() {
    var cardTypes: Int = 4
    var cardNOTypes: Int = 13

    var player1: AppCompatTextView? = null
    var player2: AppCompatTextView? = null
    var player3: AppCompatTextView? = null
    var player4: AppCompatTextView? = null
    var player5: AppCompatTextView? = null


    var txvMoneyPLayer1: AppCompatTextView? = null
    var txvMoneyPLayer2: AppCompatTextView? = null
    var txvMoneyPLayer3: AppCompatTextView? = null
    var txvMoneyPLayer4: AppCompatTextView? = null
    var txvMoneyPLayer5: AppCompatTextView? = null

    var txtP1card1: AppCompatTextView? = null
    var txtP1card2: AppCompatTextView? = null
    var txtP1card3: AppCompatTextView? = null

    var txtP2card1: AppCompatTextView? = null
    var txtP2card2: AppCompatTextView? = null
    var txtP2card3: AppCompatTextView? = null

    var txtP3card1: AppCompatTextView? = null
    var txtP3card2: AppCompatTextView? = null
    var txtP3card3: AppCompatTextView? = null

    var txtP4card1: AppCompatTextView? = null
    var txtP4card2: AppCompatTextView? = null
    var txtP4card3: AppCompatTextView? = null

    var txtP5card1: AppCompatTextView? = null
    var txtP5card2: AppCompatTextView? = null
    var txtP5card3: AppCompatTextView? = null

    var tbRowP1: TableRow? = null
    var tbRowP2:TableRow? = null
    var tbRowP3:TableRow? = null
    var tbRowP4:TableRow? = null
    var tbRowP5:TableRow? = null
    var tbRowFnBar:TableRow? = null

    var fold: Button? = null
    var show: Button? = null
    var call: Button? = null
    var raise: Button? = null
    var minus: Button? = null
    var plus: Button? = null
    var deal: Button? = null

    var txvRaiseAmt: AppCompatTextView? = null
    var txvCallAmount: AppCompatTextView? = null
    var txvPot: AppCompatTextView? = null

    var textToSpeech: TextToSpeech? = null

    var playerList: ArrayList<Player>? = null







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_screen)
        initViews();
    }

    private fun initViews() {
        player1 = findViewById<View>(R.id.player1) as AppCompatTextView
        player2 = findViewById<View>(R.id.player2) as AppCompatTextView
        player3 = findViewById<View>(R.id.player3) as AppCompatTextView
        player4 = findViewById<View>(R.id.player4) as AppCompatTextView
        player5 = findViewById<View>(R.id.player5) as AppCompatTextView

        txvMoneyPLayer1 = findViewById<View>(R.id.txvMoneyPLayer1) as AppCompatTextView
        txvMoneyPLayer2 = findViewById<View>(R.id.txvMoneyPLayer2) as AppCompatTextView
        txvMoneyPLayer3 = findViewById<View>(R.id.txvMoneyPLayer3) as AppCompatTextView
        txvMoneyPLayer4 = findViewById<View>(R.id.txvMoneyPLayer4) as AppCompatTextView
        txvMoneyPLayer5 = findViewById<View>(R.id.txvMoneyPLayer5) as AppCompatTextView




        txtP1card1 = findViewById<View>(R.id.txtP1card1) as AppCompatTextView
        txtP1card2 = findViewById<View>(R.id.txtP1card2) as AppCompatTextView
        txtP1card3 = findViewById<View>(R.id.txtP1card3) as AppCompatTextView



        txtP2card1 = findViewById<View>(R.id.txtP2card1) as AppCompatTextView
        txtP2card2 = findViewById<View>(R.id.txtP2card2) as AppCompatTextView
        txtP2card3 = findViewById<View>(R.id.txtP2card3) as AppCompatTextView

        txtP3card1 = findViewById<View>(R.id.txtP3card1) as AppCompatTextView
        txtP3card2 = findViewById<View>(R.id.txtP3card2) as AppCompatTextView
        txtP3card3 = findViewById<View>(R.id.txtP3card3) as AppCompatTextView

        txtP4card1 = findViewById<View>(R.id.txtP4card1) as AppCompatTextView
        txtP4card2 = findViewById<View>(R.id.txtP4card2) as AppCompatTextView
        txtP4card3 = findViewById<View>(R.id.txtP4card3) as AppCompatTextView

        txtP5card1 = findViewById<View>(R.id.txtP5card1) as AppCompatTextView
        txtP5card2 = findViewById<View>(R.id.txtP5card2) as AppCompatTextView
        txtP5card3 = findViewById<View>(R.id.txtP5card3) as AppCompatTextView

        tbRowP1 = findViewById<View>(R.id.tbRowP1) as TableRow
        tbRowP2 = findViewById<View>(R.id.tbRowP2) as TableRow
        tbRowP3 = findViewById<View>(R.id.tbRowP3) as TableRow
        tbRowP4 = findViewById<View>(R.id.tbRowP4) as TableRow
        tbRowP5 = findViewById<View>(R.id.tbRowP5) as TableRow

        tbRowFnBar = findViewById<View>(R.id.tbRowFnBar) as TableRow


        fold = findViewById<View>(R.id.btn_fold) as Button
        fold = findViewById<View>(R.id.btn_show) as Button
        fold = findViewById<View>(R.id.btn_call) as Button
        fold = findViewById<View>(R.id.btn_raise) as Button
        fold = findViewById<View>(R.id.btn_minus) as Button
        fold = findViewById<View>(R.id.btn_plus) as Button
        deal = findViewById<View>(R.id.deal) as Button

        txvRaiseAmt = findViewById<View>(R.id.txt_raise) as AppCompatTextView
        txvCallAmount = findViewById<View>(R.id.txt_call) as AppCompatTextView

        txvPot = findViewById<View>(R.id.pot) as AppCompatTextView

        textToSpeech = TextToSpeech(this,
            OnInitListener { textToSpeech!!.language = Locale.ENGLISH })
        playerList = ArrayList()
        //<---------- Intialization ------------------>
        tbRowFnBar!!.setVisibility(View.INVISIBLE)
        tbRowFnBar!!.removeView(show)

        tbRowP1!!.setVisibility(View.INVISIBLE)
        tbRowP2!!.setVisibility(View.INVISIBLE)
        tbRowP3!!.setVisibility(View.INVISIBLE)
        tbRowP4!!.setVisibility(View.INVISIBLE)
        tbRowP5!!.setVisibility(View.INVISIBLE)

     /*   txvMoneyPLayer1!!.setText(1000.toString())
        txvMoneyPLayer2!!.setText(1000.toString())
        txvMoneyPLayer3!!.setText(1000.toString())
        txvMoneyPLayer4!!.setText(1000.toString())
        txvMoneyPLayer5!!.setText(1000.toString())
        txvPot!!.setText("0")*/

        val database = Firebase.database
        val myRef = database.getReference("isGameOn")







    }
}