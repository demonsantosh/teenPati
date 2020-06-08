package com.hamro.teenpatigame

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hamro.teenpatigame.datas.*


class TableScreenActivity : AppCompatActivity(), View.OnClickListener {

    private val DIAMONDS = "D" //Diamond 1
    private val HEARTS = "H" //Heart 2
    private val SPADE = "S" //Spade  3
    private val CLUB = "C" //Spade  4


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
    var tbRowP2: TableRow? = null
    var tbRowP3: TableRow? = null
    var tbRowP4: TableRow? = null
    var tbRowP5: TableRow? = null
    var tbRowFnBar: TableRow? = null

    var btnFold: Button? = null
    var btnShow: Button? = null
    var btnRaise: Button? = null

    var txvRaiseAmt: AppCompatTextView? = null
    var txvCallAmount: AppCompatTextView? = null
    var txvPot: AppCompatTextView? = null



    var wholeDataRef: DatabaseReference? = null
   var gameOnReference: DatabaseReference? = null
    var activePlayerReference: DatabaseReference? = null
    var currentTurnReference: DatabaseReference? = null
    var player1DataRef: DatabaseReference? = null
    var player2DataRef: DatabaseReference? = null
    var player3DataRef: DatabaseReference? = null
    var player4DataRef: DatabaseReference? = null
    var player5DataRef: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    var myId: Int = 0
    var isOut = true
    var activePlayers: Int = 0
    var currentTurn: Int = 0
    var isGameOn: Boolean = false
    var wholeData: RealTimeDataBaseResponse? = null
    var player1Model: Player1? = null
    var player2Model: Player2? = null
    var player3Model: Player3? = null
    var player4Model: Player4? = null
    var player5Model: Player5? = null

    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false
    //delay set for 20 seconds
    var time_in_milli_seconds = 20000L
    var START_MILLI_SECONDS = 20000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_screen)
        initViews();
        database = Firebase.database
        wholeDataRef = database!!.getReference()
        gameOnReference = database!!.getReference("gameOn")
        activePlayerReference = database!!.getReference("activePlayers")
        currentTurnReference = database!!.getReference("currentTurn")
        player1DataRef = database!!.getReference("players").child("player1")
        player2DataRef = database!!.getReference("players").child("player2")
        player3DataRef = database!!.getReference("players").child("player3")
        player4DataRef = database!!.getReference("players").child("player4")
        player5DataRef = database!!.getReference("players").child("player5")

        wholeDataListiner()
        gameOnListiner()
        activePlayerListiner()
        currentTurnListiner()
        player1Listiner()
        player2Listiner()
        player3Listiner()
        player4Listiner()
        player5Listiner()

    }

    private fun currentTurnListiner() {
        val currentTurnListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentTurn = dataSnapshot.getValue(Int::class.java) as Int
                    if (myId != 0 && currentTurn == myId) {
                        tbRowFnBar!!.visibility = View.VISIBLE
                    } else {
                        tbRowFnBar!!.visibility = View.VISIBLE
                    }
                    if (currentTurn != 0) {
                        if (isRunning) {
                            isRunning = false
                            resetTimer()
                            cancelTimer()
                        }
                        startTimer(time_in_milli_seconds, currentTurn)
                        player1!!.setBackgroundColor(
                            ContextCompat.getColor(
                                this@TableScreenActivity,
                                R.color.black_overlay
                            )
                        )
                        player2!!.setBackgroundColor(
                            ContextCompat.getColor(
                                this@TableScreenActivity,
                                R.color.black_overlay
                            )
                        )
                        player3!!.setBackgroundColor(
                            ContextCompat.getColor(
                                this@TableScreenActivity,
                                R.color.black_overlay
                            )
                        )
                        player4!!.setBackgroundColor(
                            ContextCompat.getColor(
                                this@TableScreenActivity,
                                R.color.black_overlay
                            )
                        )
                        player5!!.setBackgroundColor(
                            ContextCompat.getColor(
                                this@TableScreenActivity,
                                R.color.black_overlay
                            )
                        )
                        if (currentTurn == 1) {
                            player1!!.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@TableScreenActivity,
                                    R.color.green
                                )
                            )
                        } else if (currentTurn == 2) {
                            player2!!.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@TableScreenActivity,
                                    R.color.green
                                )
                            )
                        } else if (currentTurn == 3) {
                            player3!!.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@TableScreenActivity,
                                    R.color.green
                                )
                            )
                        } else if (currentTurn == 4) {
                            player4!!.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@TableScreenActivity,
                                    R.color.green
                                )
                            )
                        } else if (currentTurn == 5) {
                            player5!!.setBackgroundColor(
                                ContextCompat.getColor(
                                    this@TableScreenActivity,
                                    R.color.green
                                )
                            )
                        }
                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        currentTurnReference!!.addValueEventListener(currentTurnListiner)
    }

    private fun activePlayerListiner() {
        val activeNoPlayerListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    activePlayers = dataSnapshot.getValue(Int::class.java) as Int
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        activePlayerReference!!.addValueEventListener(activeNoPlayerListiner)
    }

    private fun gameOnListiner() {
        val gameOnListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    isGameOn = dataSnapshot.getValue() as Boolean
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        gameOnReference!!.addValueEventListener(gameOnListiner)
    }

    private fun player5Listiner() {
        var player5ValueListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    player5Model = dataSnapshot.getValue(Player5::class.java)
                    if (player5Model?.out == false) {
                        activePlayerReference?.setValue(5)
                        //game starts only if 5 members Joins
                        gameOnReference?.setValue(true)
                        currentTurnReference?.setValue(1)
                        txvMoneyPLayer5?.setText("" + player5Model?.money)
                        wholeData?.players?.player1?.cardsInHand?.card1Type = 1
                        wholeData?.players?.player1?.cardsInHand?.card1Number = 1
                        wholeData?.players?.player1?.cardsInHand?.card2Type = 2
                        wholeData?.players?.player1?.cardsInHand?.card2Number = 1
                        wholeData?.players?.player1?.cardsInHand?.card3Type = 3
                        wholeData?.players?.player1?.cardsInHand?.card3Number = 1

                        wholeData?.players?.player2?.cardsInHand?.card1Type = 1
                        wholeData?.players?.player2?.cardsInHand?.card1Number = 2
                        wholeData?.players?.player2?.cardsInHand?.card2Type = 2
                        wholeData?.players?.player2?.cardsInHand?.card2Number = 2
                        wholeData?.players?.player2?.cardsInHand?.card3Type = 3
                        wholeData?.players?.player2?.cardsInHand?.card3Number = 2

                        wholeData?.players?.player3?.cardsInHand?.card1Type = 1
                        wholeData?.players?.player3?.cardsInHand?.card1Number = 3
                        wholeData?.players?.player3?.cardsInHand?.card2Type = 2
                        wholeData?.players?.player3?.cardsInHand?.card2Number = 3
                        wholeData?.players?.player3?.cardsInHand?.card3Type = 3
                        wholeData?.players?.player3?.cardsInHand?.card3Number = 3

                        wholeData?.players?.player4?.cardsInHand?.card1Type = 1
                        wholeData?.players?.player4?.cardsInHand?.card1Number = 4
                        wholeData?.players?.player4?.cardsInHand?.card2Type = 2
                        wholeData?.players?.player4?.cardsInHand?.card2Number = 4
                        wholeData?.players?.player4?.cardsInHand?.card3Type = 3
                        wholeData?.players?.player4?.cardsInHand?.card3Number = 4

                        wholeData?.players?.player5?.cardsInHand?.card1Type = 1
                        wholeData?.players?.player5?.cardsInHand?.card1Number = 5
                        wholeData?.players?.player5?.cardsInHand?.card2Type = 2
                        wholeData?.players?.player5?.cardsInHand?.card2Number = 5
                        wholeData?.players?.player5?.cardsInHand?.card3Type = 3
                        wholeData?.players?.player5?.cardsInHand?.card3Number = 5
                        if (myId != 0 && myId == player5Model?.id) {
                            player5?.setText("You")
                        } else {
                            player5?.setText("" + player5Model?.playerName)
                        }

                        wholeDataRef?.setValue(wholeData)
                        if (player5Model?.cardsInHand?.card1Type != 0) {
                            tbRowP5?.visibility = View.VISIBLE
                            lateinit var prefix: String
                            var suffix = player5Model?.cardsInHand?.card1Number
                            if (player5Model?.cardsInHand?.card1Type == 1) {
                                prefix = DIAMONDS
                            } else if (player5Model?.cardsInHand?.card1Type == 2) {
                                prefix = HEARTS
                            } else if (player5Model?.cardsInHand?.card1Type == 3) {
                                prefix = SPADE
                            }
                            txtP5card1?.setText(prefix + suffix)

                            if (player5Model?.cardsInHand?.card2Type == 1) {
                                prefix = DIAMONDS
                            } else if (player5Model?.cardsInHand?.card2Type == 2) {
                                prefix = HEARTS
                            } else if (player5Model?.cardsInHand?.card2Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player5Model?.cardsInHand?.card2Number
                            txtP5card2?.setText(prefix + suffix)

                            if (player5Model?.cardsInHand?.card3Type == 1) {
                                prefix = DIAMONDS
                            } else if (player5Model?.cardsInHand?.card3Type == 2) {
                                prefix = HEARTS
                            } else if (player5Model?.cardsInHand?.card3Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player5Model?.cardsInHand?.card3Number
                            txtP5card3?.setText(prefix + suffix)

                        }
                    } else {
                        player5?.setText("")
                        txvMoneyPLayer5?.setText("")
                        tbRowP5?.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        player5DataRef!!.addValueEventListener(player5ValueListiner)
    }

    private fun player4Listiner() {
        var player4ValueListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    player4Model = dataSnapshot.getValue(Player4::class.java)
                    if (player4Model?.out == false) {
                        if (myId != 0 && myId == player4Model?.id) {
                            player4?.setText("You")
                        } else {
                            player4?.setText("" + player4Model?.playerName)
                        }

                        txvMoneyPLayer4?.setText("" + player4Model?.money)
                        if (player4Model?.cardsInHand?.card1Type != 0) {
                            tbRowP4?.visibility = View.VISIBLE


                            lateinit var prefix: String
                            var suffix = player4Model?.cardsInHand?.card1Number
                            if (player4Model?.cardsInHand?.card1Type == 1) {
                                prefix = DIAMONDS
                            } else if (player4Model?.cardsInHand?.card1Type == 2) {
                                prefix = HEARTS
                            } else if (player4Model?.cardsInHand?.card1Type == 3) {
                                prefix = SPADE
                            }
                            txtP4card1?.setText(prefix + suffix)

                            if (player4Model?.cardsInHand?.card2Type == 1) {
                                prefix = DIAMONDS
                            } else if (player4Model?.cardsInHand?.card2Type == 2) {
                                prefix = HEARTS
                            } else if (player4Model?.cardsInHand?.card2Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player4Model?.cardsInHand?.card2Number
                            txtP4card2?.setText(prefix + suffix)

                            if (player4Model?.cardsInHand?.card3Type == 1) {
                                prefix = DIAMONDS
                            } else if (player4Model?.cardsInHand?.card3Type == 2) {
                                prefix = HEARTS
                            } else if (player4Model?.cardsInHand?.card3Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player4Model?.cardsInHand?.card3Number
                            txtP4card3?.setText(prefix + suffix)
                        }
                    } else {
                        player4?.setText("")
                        txvMoneyPLayer4?.setText("")
                        tbRowP4?.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        player4DataRef!!.addValueEventListener(player4ValueListiner)
    }

    private fun player3Listiner() {
        var player3ValueListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    player3Model = dataSnapshot.getValue(Player3::class.java)
                    if (player3Model?.out == false) {
                        if (myId != 0 && myId == player3Model?.id) {
                            player3?.setText("You")
                        } else {
                            player3?.setText("" + player3Model?.playerName)
                        }

                        txvMoneyPLayer3?.setText("" + player3Model?.money)
                        if (player3Model?.cardsInHand?.card1Type != 0) {
                            tbRowP3?.visibility = View.VISIBLE


                            lateinit var prefix: String
                            var suffix = player3Model?.cardsInHand?.card1Number
                            if (player3Model?.cardsInHand?.card1Type == 1) {
                                prefix = DIAMONDS
                            } else if (player3Model?.cardsInHand?.card1Type == 2) {
                                prefix = HEARTS
                            } else if (player3Model?.cardsInHand?.card1Type == 3) {
                                prefix = SPADE
                            }
                            txtP3card1?.setText(prefix + suffix)

                            if (player3Model?.cardsInHand?.card2Type == 1) {
                                prefix = DIAMONDS
                            } else if (player3Model?.cardsInHand?.card2Type == 2) {
                                prefix = HEARTS
                            } else if (player3Model?.cardsInHand?.card2Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player3Model?.cardsInHand?.card2Number
                            txtP3card2?.setText(prefix + suffix)

                            if (player3Model?.cardsInHand?.card3Type == 1) {
                                prefix = DIAMONDS
                            } else if (player3Model?.cardsInHand?.card3Type == 2) {
                                prefix = HEARTS
                            } else if (player3Model?.cardsInHand?.card3Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player3Model?.cardsInHand?.card3Number
                            txtP3card3?.setText(prefix + suffix)
                        }
                    } else {
                        player3?.setText("")
                        txvMoneyPLayer3?.setText("")
                        tbRowP3?.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        player3DataRef?.addValueEventListener(player3ValueListiner)
    }

    private fun player2Listiner() {
        var player2ValueListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    player2Model = dataSnapshot.getValue(Player2::class.java)
                    if (player2Model?.out == false) {
                        if (myId != 0 && myId == player2Model?.id) {
                            player2?.setText("You")
                        } else {
                            player2?.setText("" + player2Model?.playerName)
                        }
                        txvMoneyPLayer2?.setText("" + player2Model?.money)
                        if (player2Model?.cardsInHand?.card1Type != 0) {
                            tbRowP2?.visibility = View.VISIBLE

                            lateinit var prefix: String
                            var suffix = player2Model?.cardsInHand?.card1Number
                            if (player2Model?.cardsInHand?.card1Type == 1) {
                                prefix = DIAMONDS
                            } else if (player2Model?.cardsInHand?.card1Type == 2) {
                                prefix = HEARTS
                            } else if (player2Model?.cardsInHand?.card1Type == 3) {
                                prefix = SPADE
                            }
                            txtP2card1?.setText(prefix + suffix)

                            if (player2Model?.cardsInHand?.card2Type == 1) {
                                prefix = DIAMONDS
                            } else if (player2Model?.cardsInHand?.card2Type == 2) {
                                prefix = HEARTS
                            } else if (player2Model?.cardsInHand?.card2Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player2Model?.cardsInHand?.card2Number
                            txtP2card2?.setText(prefix + suffix)

                            if (player2Model?.cardsInHand?.card3Type == 1) {
                                prefix = DIAMONDS
                            } else if (player2Model?.cardsInHand?.card3Type == 2) {
                                prefix = HEARTS
                            } else if (player2Model?.cardsInHand?.card3Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player2Model?.cardsInHand?.card3Number
                            txtP2card3?.setText(prefix + suffix)

                        }
                    } else {
                        player2?.setText("")
                        txvMoneyPLayer2?.setText("")
                        tbRowP2?.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        player2DataRef?.addValueEventListener(player2ValueListiner)
    }

    private fun player1Listiner() {
        val player1ValueListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    player1Model = dataSnapshot.getValue(Player1::class.java)
                    if (player1Model?.out == false) {
                        if (myId != 0 && myId == player1Model?.id) {
                            player1?.setText("You")
                        } else {
                            player1?.setText("" + player1Model?.playerName)
                        }
                        txvMoneyPLayer1?.setText("" + player1Model?.money)
                        if (player1Model?.cardsInHand?.card1Type != 0) {
                            tbRowP1?.visibility = View.VISIBLE
                            lateinit var prefix: String
                            var suffix = player1Model?.cardsInHand?.card1Number
                            if (player1Model?.cardsInHand?.card1Type == 1) {
                                prefix = DIAMONDS
                            } else if (player1Model?.cardsInHand?.card1Type == 2) {
                                prefix = HEARTS
                            } else if (player1Model?.cardsInHand?.card1Type == 3) {
                                prefix = SPADE
                            }
                            txtP1card1?.setText(prefix + suffix)

                            if (player1Model?.cardsInHand?.card2Type == 1) {
                                prefix = DIAMONDS
                            } else if (player1Model?.cardsInHand?.card2Type == 2) {
                                prefix = HEARTS
                            } else if (player1Model?.cardsInHand?.card2Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player1Model?.cardsInHand?.card2Number
                            txtP1card2?.setText(prefix + suffix)

                            if (player1Model?.cardsInHand?.card3Type == 1) {
                                prefix = DIAMONDS
                            } else if (player1Model?.cardsInHand?.card3Type == 2) {
                                prefix = HEARTS
                            } else if (player1Model?.cardsInHand?.card3Type == 3) {
                                prefix = SPADE
                            }
                            suffix = player1Model?.cardsInHand?.card3Number
                            txtP1card3?.setText(prefix + suffix)
                        }
                    } else {
                        player1?.setText("")
                        txvMoneyPLayer1?.setText("")
                        tbRowP1?.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        player1DataRef?.addValueEventListener(player1ValueListiner)
    }

    private fun wholeDataListiner() {
        val wholeDataValueListiner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    wholeData = dataSnapshot.getValue(RealTimeDataBaseResponse::class.java)
                    if (wholeData != null) {
                        if (wholeData?.players?.player1?.out!!) {
                            if (myId == 0) {
                                myId = wholeData!!.players?.player1?.id!!
                                var palyer1 = wholeData?.players?.player1
                                palyer1?.out = false
                                player1DataRef?.setValue(palyer1)
                            }
                        } else if (wholeData?.players?.player2?.out!!) {
                            if (myId == 0) {
                                myId = wholeData?.players?.player2?.id!!
                                var palyer2 = wholeData?.players?.player2
                                palyer2?.out = false
                                player2DataRef?.setValue(palyer2)
                            }
                        } else if (wholeData?.players?.player3?.out!!) {
                            if (myId == 0) {
                                myId = wholeData?.players?.player3?.id!!
                                var palyer3 = wholeData!!.players!!.player3
                                palyer3?.out = false
                                player3DataRef?.setValue(palyer3)
                            }
                        } else if (wholeData?.players?.player4?.out!!) {
                            if (myId == 0) {
                                myId = wholeData!!.players?.player4!!.id!!
                                var palyer4 = wholeData!!.players!!.player4
                                palyer4?.out = false
                                player4DataRef?.setValue(palyer4)
                            }
                        } else if (wholeData?.players?.player5?.out!!) {
                            if (myId == 0) {
                                myId = wholeData!!.players?.player5?.id!!
                                var palyer5 = wholeData?.players?.player5
                                palyer5?.out = false
                                player5DataRef?.setValue(palyer5)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }
        wholeDataRef!!.addValueEventListener(wholeDataValueListiner)
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

        btnFold = findViewById<View>(R.id.btnFold) as Button
        btnShow = findViewById<View>(R.id.btnShow) as Button
        btnRaise = findViewById<View>(R.id.btnRaise) as Button
        btnRaise!!.setOnClickListener(this)

        txvPot = findViewById<View>(R.id.pot) as AppCompatTextView


        tbRowFnBar!!.setVisibility(View.INVISIBLE)
    }

    fun startTimer(time_in_seconds: Long, currentTurn: Int) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                if (this@TableScreenActivity.currentTurn == currentTurn) {
                    if (currentTurn == 5) {
                        currentTurnReference?.setValue(4)
                        player5Model?.out = true
                        player5DataRef?.setValue(player5Model)
                    } else {
                        currentTurnReference?.setValue(currentTurn + 1)
                        if(currentTurn==1){
                            player1Model?.out = true
                            player1DataRef?.setValue(player1Model)
                        }else if(currentTurn==2){
                            player2Model?.out = true
                            player2DataRef?.setValue(player2Model)
                        }else if(currentTurn==3){
                            player3Model?.out = true
                            player3DataRef?.setValue(player3Model)
                        }else if(currentTurn==4){
                            player4Model?.out = true
                            player4DataRef?.setValue(player4Model)
                        }
                    }
                    if(currentTurn==myId){
                        Toast.makeText(applicationContext, "You are Out of Game ", Toast.LENGTH_LONG).show()
                        finish()
                       var totalActivePlayers= wholeData?.activePlayers
                        activePlayerReference?.setValue(totalActivePlayers?.minus(1))

                    }
                }
            }
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
            }
        }
        countdown_timer.start()
        isRunning = true
    }

    fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
    }

    fun cancelTimer() {
        countdown_timer.cancel();
    }
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRaise -> {
                if(currentTurn!=0){
                    if(currentTurn==5){
                        currentTurnReference?.setValue(1)
                    }else{
                        currentTurnReference?.setValue(currentTurn+1)
                    }
                }
            }

            else -> println("Default")
        }
    }

}