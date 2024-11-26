package com.learning.ad.ff.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.learning.ad.ff.databinding.FragmentFlowDemoBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

class FlowDemoFragment : Fragment() {
   private var _binding:FragmentFlowDemoBinding? = null
   private val binding get() = _binding!!
   private val _stateFlow = MutableStateFlow(0)
   private val stateFlow = _stateFlow.asStateFlow()
   private val _sharedFlow = MutableSharedFlow<Int>(replay = 10, onBufferOverflow = BufferOverflow.DROP_OLDEST)
   private val sharedFlow = _sharedFlow.asSharedFlow()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentFlowDemoBinding.inflate(layoutInflater,container,false)
      // Inflate the layout for this fragment
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      lifecycleScope.launch {
         stateFlow.collectLatest {
            println("Counter = $it")
         }
      }
      lifecycleScope.launch {
         sharedFlow.collect {
            println("$it")
         }
      }
      binding.startBtn.setOnClickListener {
      handleFlow2()}
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   private fun myFlow(): Flow<Int> = flow {
      var counter = 1
      while (counter < 6) {
         emit(counter)
         counter++
         delay(2000)
      }
   }
   private fun handleFlow() {
      lifecycleScope.launch {
         try {
            val elapsedTime = measureTimeMillis {
               val flow1 = (1..5).asFlow().onEach { delay(1000) }
               val flow2 = flowOf("one", "two", "three", "four").onEach { delay(1500) }
               flow1.combine(flow2) { value, string -> "$value, $string" }.collect { println(it) }
            }
            println("Duration = $elapsedTime")
         }
         finally { println("Flow stream ended.") }
      }
   }
   private fun doubleIt(value: Int) = flow {
      emit(value)
      delay(1000)
      emit(value + value)
   }
   private fun handleFlow1() {
      _stateFlow.value += 1
   }

   fun handleFlow2() {
      var counter = 1
      lifecycleScope.launch {
         while (counter < 6) {
            _sharedFlow.emit(counter)
            counter++
            delay(2000)
         }
      }
   }
}