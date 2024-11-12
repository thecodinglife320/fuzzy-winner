package com.learning.ad.ff.fragment

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import com.learning.ad.ff.*
import com.learning.ad.ff.databinding.*

class MainFragment : Fragment() {
   private var _binding: FragmentMainBinding? = null
   private val binding get() = _binding!!
   private var listener: MainFragmentListener? = null
   interface MainFragmentListener{
      fun goToMotionEventFragment()
      fun goToMotionLayoutFragment()
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding=FragmentMainBinding.inflate(inflater,container,false)
      binding.spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.fragments))
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
         override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
         ) {
            when(position){
               1-> listener?.goToMotionEventFragment()
               2-> listener?.goToMotionLayoutFragment()
            }
            binding.spinner.setSelection(0)
         }

         override fun onNothingSelected(parent: AdapterView<*>?) {
         }
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   override fun onAttach(context: Context) {
      super.onAttach(context)
      try {
         listener = context as MainFragmentListener
      }catch (e: ClassCastException){
         throw ClassCastException("$context must implement MainFragmentListener")
      }
   }
   override fun onDetach() {
      super.onDetach()
      listener=null
   }
}