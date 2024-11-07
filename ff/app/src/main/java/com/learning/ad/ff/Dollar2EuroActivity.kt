package com.learning.ad.ff

import android.os.*
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*

class Dollar2EuroActivity : AppCompatActivity() {
   private lateinit var binding: ActivityDollar2euroBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityDollar2euroBinding.inflate(layoutInflater)
      setContentView(binding.root)
      binding.convertBtn.setOnClickListener {
         if (binding.dollarsEdt.text.isNotEmpty()) {
            val dollarValue = binding.dollarsEdt.text.toString().toFloat()
            val euroValue = dollarValue * 0.92f
            binding.resultTv.text = String.format(euroValue.toString())
            binding.dollarsEdt.text.clear()
         } else binding.resultTv.text = getString(R.string.no_value_string)
      }
   }
}