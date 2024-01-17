package com.example.tictactoe

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.databinding.ScoreBoardBinding
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingsb: ScoreBoardBinding
    private val player1 = ArrayList<Int>()
    private val player2 = ArrayList<Int>()
    private var winner = -1
    private var scorep1 = 0
    private var scorep2 = 0
    private var player = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onClick(v: View) {
        var buSelected = v as Button
        var cellId = 0
        when (v.id) {
            binding.bu1.id -> cellId = 1
            binding.bu2.id -> cellId = 2
            binding.bu3.id -> cellId = 3
            binding.bu4.id -> cellId = 4
            binding.bu5.id -> cellId = 5
            binding.bu6.id -> cellId = 6
            binding.bu7.id -> cellId = 7
            binding.bu8.id -> cellId = 8
            binding.bu9.id -> cellId = 9
        }
        play(cellId, buSelected)
        checkwinner()
    }

    fun play(cellId: Int, buSelected: Button) {
        if (player == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundColor(getColor(R.color.purple))
            player1.add(cellId)
            player = 2
            binding.textView.text = getString(R.string.player_2_turn)
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundColor(getColor(R.color.green))
            player2.add(cellId)
            player = 1
            binding.textView.text = getString(R.string.player_1_turn)
        }
        buSelected.isEnabled = false

    }

    fun checkwinner() {

        if (player1.contains(1) && player1.contains(2) && player1.contains(3) ||
            player1.contains(4) && player1.contains(5) && player1.contains(6) ||
            player1.contains(7) && player1.contains(8) && player1.contains(9)
        ) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3) ||
            player2.contains(4) && player2.contains(5) && player2.contains(6) ||
            player2.contains(7) && player2.contains(8) && player2.contains(9)
        ) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7) ||
            player1.contains(2) && player1.contains(5) && player1.contains(8) ||
            player1.contains(3) && player1.contains(6) && player1.contains(9)
        ) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7) ||
            player2.contains(2) && player2.contains(5) && player2.contains(8) ||
            player2.contains(3) && player2.contains(6) && player2.contains(9)
        ) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9) ||
            player1.contains(7) && player1.contains(5) && player1.contains(3)
        ) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9) ||
            player2.contains(7) && player2.contains(5) && player2.contains(3)
        ) {
            winner = 2
        }
        draw()
        if(winner != -1){
            when (winner) {
                1 -> {
                    scorep1++
                    showWinner()
                }
                2 -> {
                    scorep2++
                    showWinner()
                }
                0 -> {
                    showWinner()
                }
            }
        }
    }
    fun draw(){
        if(player1.size==5 || player2.size==5 && winner == -1){
            winner = 0
        }
    }

    fun showWinner(){
        val dialog = Dialog(this)
        bindingsb = ScoreBoardBinding.inflate(layoutInflater)
        dialog.setContentView(bindingsb.root)

        when (winner) {
            1 -> {
                bindingsb.winnertxt.text= getString(R.string.player_1_won)
                bindingsb.scorep1.text=scorep1.toString()
                bindingsb.scorep2.text=scorep2.toString()
            }
            2 -> {
                bindingsb.winnertxt.text= getString(R.string.player_2_won)
                bindingsb.scorep1.text=scorep1.toString()
                bindingsb.scorep2.text=scorep2.toString()
            }
            0 -> {
                bindingsb.winnertxt.text= getString(R.string.draw)
                bindingsb.scorep1.text=scorep1.toString()
                bindingsb.scorep2.text=scorep2.toString()
            }
        }
        dialog.show()
        bindingsb.rematchbtn.setOnClickListener {
            dialog.dismiss()
            resetGame()
        }
    }

    fun resetGame(){
        binding.bu1.text=""
        binding.bu1.setBackgroundColor(getColor(R.color.gray))
        binding.bu2.text=""
        binding.bu2.setBackgroundColor(getColor(R.color.gray))
        binding.bu4.text=""
        binding.bu4.setBackgroundColor(getColor(R.color.gray))
        binding.bu3.text=""
        binding.bu3.setBackgroundColor(getColor(R.color.gray))
        binding.bu5.text=""
        binding.bu5.setBackgroundColor(getColor(R.color.gray))
        binding.bu6.text=""
        binding.bu6.setBackgroundColor(getColor(R.color.gray))
        binding.bu7.text=""
        binding.bu7.setBackgroundColor(getColor(R.color.gray))
        binding.bu8.text=""
        binding.bu8.setBackgroundColor(getColor(R.color.gray))
        binding.bu9.text=""
        binding.bu9.setBackgroundColor(getColor(R.color.gray))
        binding.bu1.isEnabled=true
        binding.bu2.isEnabled=true
        binding.bu3.isEnabled=true
        binding.bu4.isEnabled=true
        binding.bu5.isEnabled=true
        binding.bu6.isEnabled=true
        binding.bu7.isEnabled=true
        binding.bu8.isEnabled=true
        binding.bu9.isEnabled=true
        player1.clear()
        player2.clear()
        winner=-1
    }
}