package com.hamro.teenpatigame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.hamro.teenpatigame.datas.RealTimeDataBaseResponse


class MainActivity : AppCompatActivity() {
    var btnPlay: Button? = null
    var btnPlayFireBase: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlay = findViewById(R.id.btnPlay) as Button
        btnPlay!!.setOnClickListener({
            val intent = Intent(this@MainActivity, Table::class.java)
            startActivity(intent)
        })
        btnPlayFireBase = findViewById(R.id.btnPlayFireBase) as Button
        btnPlayFireBase!!.setOnClickListener({
            val database = Firebase.database
            val myRef = database.getReference("gameOn")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue<Boolean>()
                    if (value == false) {
                        val intent = Intent(this@MainActivity, TableScreenActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext, "Please wait for game to finish", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("===>", "Failed to read value.", error.toException())
                }
            })
        })



    }
}