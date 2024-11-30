package com.learning.ad.ff.fragment

import android.content.*
import android.os.Bundle
import android.util.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.learning.ad.ff.R
import com.learning.ad.ff.databinding.FragmentUserBinding
import com.learning.ad.ff.fragment.MainFragment.*
import com.learning.ad.ff.observer.*
import com.learning.ad.ff.viewmodel.*

class UserFragment : Fragment() {
   private lateinit var viewModel:UserViewModel
   interface UserFragmentListener{
      fun fetchUserProfile()
   }
   private var listener: UserFragmentListener? = null
   private var _binding:FragmentUserBinding?=null
   private val binding get() = _binding!!
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[UserViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentUserBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      listener?.fetchUserProfile()
   }
   override fun onAttach(context: Context) {
      super.onAttach(context)
      listener = context as UserFragmentListener
   }

   override fun onDetach() {
      super.onDetach()
      listener = null
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}