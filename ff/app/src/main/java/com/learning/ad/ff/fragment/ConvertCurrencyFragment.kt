package com.learning.ad.ff.fragment

import android.os.*
import android.util.*
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.viewmodel.*

class ConvertCurrencyFragment : Fragment() {
   private var _binding: FragmentConvertCurrencyBinding? = null
   private val binding get() = _binding!!
   private lateinit var viewModel: ConvertCurrencyViewModel
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[ConvertCurrencyViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentConvertCurrencyBinding.inflate(layoutInflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val resultObserver = Observer<Float> { result ->
         binding.euroTv.text = String.format(result.toString())
         Log.d("observe", result.toString())
      }
      viewModel.result.observe(viewLifecycleOwner, resultObserver)
      binding.convertBtn.setOnClickListener {
         viewModel.convert(binding.dollarEdt.text.toString())
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}