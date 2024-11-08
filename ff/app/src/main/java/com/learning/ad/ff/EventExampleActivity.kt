package com.learning.ad.ff

import android.os.Bundle
import android.util.*
import androidx.appcompat.app.AppCompatActivity
import com.learning.ad.ff.databinding.ActivityEventExampleBinding

class EventExampleActivity : AppCompatActivity() {
   private  lateinit var binding: ActivityEventExampleBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityEventExampleBinding.inflate(layoutInflater)
      setContentView(binding.root)
      binding.pressMeBtn.setOnClickListener { binding.statusTextTv.text = "onClick" }
      binding.pressMeBtn.setOnLongClickListener {
         binding.statusTextTv.text = "onLongClick"
         false
      }
   }
}