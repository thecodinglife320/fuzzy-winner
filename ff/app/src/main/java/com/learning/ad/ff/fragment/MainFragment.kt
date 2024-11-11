package com.learning.ad.ff.fragment

import android.os.*
import android.view.*
import android.widget.TextView
import androidx.fragment.app.*
import androidx.navigation.*
import com.learning.ad.ff.R
import com.learning.ad.ff.databinding.*

class MainFragment : Fragment() {
   private var _binding: FragmentMainBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding=FragmentMainBinding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.textView3.setOnClickListener {
         val action = MainFragmentDirections
            .actionMainFragmentToMotionEventFragment((it as TextView).text.toString())
         Navigation.findNavController(it).navigate(action)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}