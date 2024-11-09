package com.learning.ad.ff.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.learning.ad.ff.databinding.*

class SecondFragment : Fragment() {
   private var _binding: FragmentSecondBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   fun changeTextProperties(fontSize: Int, text: String) {
      binding.textTv.textSize = fontSize.toFloat()
      binding.textTv.text = text
   }
}