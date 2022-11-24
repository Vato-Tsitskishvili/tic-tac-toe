package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1:Button
    private lateinit var button2:Button
    private lateinit var button3:Button
    private lateinit var button4:Button
    private lateinit var button5:Button
    private lateinit var button6:Button
    private lateinit var button7:Button
    private lateinit var button8:Button
    private lateinit var button9:Button
    private lateinit var buttonReset:Button
    private lateinit var buttonReset2:Button
    private lateinit var player1:TextView
    private lateinit var player2:TextView

    private var activePlayer = 1
    private var firstPlayer = ArrayList<Int>()
    private var secondPlayer = ArrayList<Int>()
    private var buttonNumber = 0
    private var winnerPlayer = 0
    private var score1 = 0
    private var score2 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        buttonReset = findViewById(R.id.buttonReset)
        buttonReset2 = findViewById(R.id.buttonReset2)
        player1 = findViewById(R.id.textView1)
        player2 = findViewById(R.id.textView2)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)

        val player1Name = intent.extras?.getString("player1Name")?:"no message found"
        val player2Name = intent.extras?.getString("player2Name")?:"no message found"

        player1.text = "$player1Name: 0"
        player2.text = "$player2Name: 0"
    }

    override fun onClick(clickedView: View?) {
        if(clickedView is Button) {
            when(clickedView.id) {
                R.id.button1 -> buttonNumber = 1
                R.id.button2 -> buttonNumber = 2
                R.id.button3 -> buttonNumber = 3
                R.id.button4 -> buttonNumber = 4
                R.id.button5 -> buttonNumber = 5
                R.id.button6 -> buttonNumber = 6
                R.id.button7 -> buttonNumber = 7
                R.id.button8 -> buttonNumber = 8
                R.id.button9 -> buttonNumber = 9
            }
            if(buttonNumber != 0) {
                playGame(clickedView, buttonNumber)
            }
        }
    }

    private fun playGame(clickedView: Button, buttonNumber: Int) {
        if(winnerPlayer == 0){
            if(activePlayer == 1){
                clickedView.text = "X"
                clickedView.setBackgroundColor(Color.GRAY)
                activePlayer = 2
                firstPlayer.add(buttonNumber)
            }
            else if(activePlayer == 2) {
                clickedView.text = "O"
                clickedView.setBackgroundColor(Color.GREEN)
                activePlayer = 1
                secondPlayer.add(buttonNumber)
            }
            clickedView.isEnabled = false
            check()
        }
        buttonReset.setOnClickListener{
            reset()
        }
        buttonReset2.setOnClickListener{
            resetGame()
        }
    }

    private fun check() {
        val winningCombinations = listOf(
            listOf(1,2,3),
            listOf(4,5,6),
            listOf(7,8,9),
            listOf(1,4,7),
            listOf(2,5,8),
            listOf(3,6,9),
            listOf(1,5,9),
            listOf(3,5,7),
        )
        when {
            winningCombinations.any{firstPlayer.containsAll(it)} -> winnerPlayer = 1
            winningCombinations.any{secondPlayer.containsAll(it)} -> winnerPlayer = 2
        }
        if(winnerPlayer == 1){
            Toast.makeText(this,"${player1.text.toString().dropLast(3)} wins",Toast.LENGTH_SHORT).show()
            score1++
        }
        if(winnerPlayer == 2){
            Toast.makeText(this,"${player2.text.toString().dropLast(3)} wins",Toast.LENGTH_SHORT).show()
            score2++
        }
        if(firstPlayer.size + secondPlayer.size == 9 && winnerPlayer == 0){
            Toast.makeText(this,"it's draw",Toast.LENGTH_LONG).show()
        }
    }

    private fun reset(){
        activePlayer = 1
        winnerPlayer = 0
        firstPlayer.clear()
        secondPlayer.clear()
        buttonNumber = 0
        for(i in 1..9){
            var buttonSelected: Button?
            buttonSelected = when(i){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> button1
            }
            buttonSelected.isEnabled = true
            buttonSelected.setBackgroundColor(Color.parseColor("#FF6200EE"))
            buttonSelected.text = ""
        }
        player1.text = player1.text.toString().dropLast(1)+""+ score1.toString()
        player2.text = player2.text.toString().dropLast(1)+""+ score2.toString()
    }

    private fun resetGame(){
        score1 = 0
        score2 = 0
        Intent(this,MainActivity::class.java).also{
            startActivity(it)
        }
    }
}