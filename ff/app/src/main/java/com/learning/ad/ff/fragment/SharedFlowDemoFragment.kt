package com.learning.ad.ff.fragment

import android.os.*
import android.util.Log
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import com.learning.ad.ff.adapter.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.observer.*
import com.learning.ad.ff.viewmodel.*
import kotlinx.coroutines.*

class SharedFlowDemoFragment : Fragment() {
   private var _binding: FragmentSharedFlowDemoBinding? = null
   private val binding get() = _binding!!
   private lateinit var viewModel:SharedFlowViewModel
   private val itemList = ArrayList<String>()
   private lateinit var adapter: RVadapter
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[SharedFlowViewModel::class.java]
   }

   private fun collectFlow() {
      adapter = RVadapter(itemList)
      binding.rv.layoutManager = LinearLayoutManager(requireContext())
      binding.rv.adapter = adapter
      lifecycleScope.launch {
         repeatOnLifecycle(Lifecycle.State.STARTED){
            viewModel.sharedFlow.collect { value ->
               Log.d(TAG,"Collecting $value")
               itemList.add(value.toString())
               adapter.notifyItemInserted(itemList.lastIndex)
               binding.rv.smoothScrollToPosition(adapter.itemCount)
            }
         }
      }
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSharedFlowDemoBinding.inflate(layoutInflater,container,false)
      collectFlow()
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}