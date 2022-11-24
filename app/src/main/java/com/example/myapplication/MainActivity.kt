package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val player1 = findViewById<EditText>(R.id.editText1)
        val player2 = findViewById<EditText>(R.id.editText2)
        val buttonStart = findViewById<Button>(R.id.button)

        if(player1.text.length <= 8 && player2.text.length <= 8){
            buttonStart.setOnClickListener{
                Intent(this,MainActivity2::class.java).also {
                    it.putExtra("player1Name", player1.text.toString())
                    it.putExtra("player2Name", player2.text.toString())
                    startActivity(it)
                }
            }
        }
        else{
            Toast.makeText(this,"name should contain less then 11 characters",Toast.LENGTH_SHORT).show()
        }
    }
}