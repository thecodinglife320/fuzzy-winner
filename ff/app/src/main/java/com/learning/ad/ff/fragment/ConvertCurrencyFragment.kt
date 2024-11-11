package com.learning.ad.ff.fragment

import android.os.*
import android.view.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import com.learning.ad.ff.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.viewmodel.*
class ConvertCurrencyFragment : Fragment() {
   private lateinit var binding: FragmentConvertCurrencyBinding
   private lateinit var viewModel: ConvertCurrencyViewModel
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity?.application?.let {
         val factory = SavedStateViewModelFactory(it, this)
         viewModel = ViewModelProvider(this, factory)[ConvertCurrencyViewModel::class.java]
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_convert_currency,container,false)
      binding.lifecycleOwner=this
      binding.mViewModel = viewModel
      return binding.root
   }
}