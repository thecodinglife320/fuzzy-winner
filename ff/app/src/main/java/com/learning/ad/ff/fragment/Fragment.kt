package com.learning.ad.ff.fragment

import android.content.*
import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.google.android.material.slider.*
import com.learning.ad.ff.databinding.*

class FirstFragment : Fragment(){
   private var _binding: FragmentFirstBinding? = null
   private val binding get() = _binding!!
   private var seekValue = 10
   private var listener: FirstFragmentListener? = null
   interface FirstFragmentListener{
      fun onButtonClick(fontSize: Int, text: String)
      fun showTime():String?
      fun sendMessage()
   }

   override fun onAttach(context: Context) {
      super.onAttach(context)
      try {
         listener = context as FirstFragmentListener
      }catch (e: ClassCastException){
         throw ClassCastException("$context must implement FirstFragmentListener")
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.seekBar.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
         override fun onStartTrackingTouch(slider: Slider) {}
         override fun onStopTrackingTouch(slider: Slider) {}
      })
      binding.seekBar.addOnChangeListener { _, value, _ ->
         println(value)
         seekValue = value.toInt()
      }
      binding.changeTextBtn.setOnClickListener {
         listener?.onButtonClick(seekValue,binding.textEdt.text.toString())
      }
      binding.getTimeFromServiceBtn.setOnClickListener {
         binding.textEdt.setText(listener?.showTime().toString())
      }
      binding.sendMessageToService.setOnClickListener { listener?.sendMessage() }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
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