package com.learning.ad.ff.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.Fragment
import com.learning.ad.ff.databinding.*

class Tab4Fragment : Fragment() {
   private var _binding: FragmentTab4Binding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTab4Binding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}