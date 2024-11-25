package com.learning.ad.ff.fragment

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import com.learning.ad.ff.databinding.*
import java.lang.ClassCastException

class FirstFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

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
      binding.seekBar.setOnSeekBarChangeListener(this)
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

   override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
      seekValue = progress
   }
   override fun onStartTrackingTouch(seekBar: SeekBar?) {}
   override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}