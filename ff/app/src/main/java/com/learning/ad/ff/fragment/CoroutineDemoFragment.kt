package com.learning.ad.ff.fragment

import android.os.*
import android.util.*
import android.view.*
import android.widget.*
import android.widget.SeekBar.*
import androidx.fragment.app.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.observer.*
import kotlinx.coroutines.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CoroutineDemoFragment : Fragment() {
   private var param1: String? = null
   private var param2: String? = null
   private var _binding: FragmentCoroutineDemoBinding? = null
   private val binding get() = _binding!!
   private val coroutineScope = CoroutineScope(Dispatchers.Main)

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      arguments?.let {
         param1 = it.getString(ARG_PARAM1)
         param2 = it.getString(ARG_PARAM2)
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding=FragmentCoroutineDemoBinding.inflate(inflater,container,false)
      binding.seekBar2.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
         override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            binding.countTv.text = String.format("$progress coroutines")
         }
         override fun onStartTrackingTouch(seekBar: SeekBar?) {
         }
         override fun onStopTrackingTouch(seekBar: SeekBar?) {
         }

      })
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.button2.setOnClickListener {
         (1..binding.seekBar2.progress).forEach {
            val message = "Started Coroutine $it"
            binding.statusTv.text = message
            coroutineScope.launch(Dispatchers.Main) {
               Log.d(TAG, "$it on thread start")
               binding.statusTv.text = performTaskAsync(it).await()
            }
         }
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   private fun performTaskAsync(taskNumber: Int) =
      coroutineScope.async(Dispatchers.Main) {
         delay(5_000)
         return@async "Finished Coroutine $taskNumber"
      }
}