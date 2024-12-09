package com.learning.ad.ff1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.ad.ff1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)
      binding.rollBtn.setOnClickListener {
         data class DiceData(val imageResource: Int, val textColor: Int)
         val diceData = mapOf(
            1 to DiceData(R.drawable.dice1, getColor(R.color.red)),
            2 to DiceData(R.drawable.dice2, getColor(R.color.blue)),
            3 to DiceData(R.drawable.dice3, getColor(R.color.blue)),
            4 to DiceData(R.drawable.dice4, getColor(R.color.blue)),
            5 to DiceData(R.drawable.dice5, getColor(R.color.blue)),
            6 to DiceData(R.drawable.dice6, getColor(R.color.green))
         )
         (1..6).random().apply {
            binding.imageView.setImageResource(diceData[this]!!.imageResource)
            binding.resultTv.text = getString(R.string.result_after_roll, this)
            binding.resultTv.setTextColor(diceData[this]!!.textColor)
         }
      }
   }
}