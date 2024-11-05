package com.learning.ad.ff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.ad.ff.databinding.ActivityMainBinding

class Dollar2EuroActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)
      binding.convertBtn.setOnClickListener {
         if (binding.dollarsEdt.text.isNotEmpty()) {
            val dollarValue = binding.dollarsEdt.text.toString().toFloat()
            val euroValue = dollarValue * 0.92f
            binding.resultTv.text = euroValue.toString()
            binding.dollarsEdt.text.clear()
         } else binding.resultTv.text = getString(R.string.no_value_string)
      }
   }
}