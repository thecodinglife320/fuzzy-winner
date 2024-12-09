package com.learning.ad.ff.fragment

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.core.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import com.google.android.material.snackbar.*
import com.learning.ad.ff.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.viewmodel.*
import kotlinx.coroutines.*

class MainFragment : Fragment() {
   private var _binding: FragmentMainBinding? = null
   private val binding get() = _binding!!
   private var listener: MainFragmentListener? = null
   private lateinit var viewModel: MainViewModel
   interface MainFragmentListener{
      fun goToMotionEventFragment()
      fun goToMotionLayoutFragment()
      fun goToKotlinLayoutFragment()
      fun goToCommonGestureFragment()
      fun goToCustomGestureFragment()
      fun goToTabLayoutFragment()
      fun goToCardDemoFragment()
      fun intentToSecondActivity()
      fun goToCoroutineDemoFragment()
      fun goToFlowDemoFragment()
      fun goToSharedFlowDemoFragment()
      fun goToSqlDemoFragment()
      fun intentToRetroAchievementActivity(userName:String)
      fun goToRoomDemoFragment()
      fun goToVideoPlayBackFragment()
      fun goToPermissionDemoFragment()
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[MainViewModel::class.java]
      //collect flow
      lifecycleScope.launch {
         viewModel.userInput.collect{
            if (it == "longplay123") listener?.intentToRetroAchievementActivity(it)
         }
      }
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
      binding.userNameEdt.doAfterTextChanged { editable->
         viewModel.updateUserInput(editable.toString())
      }
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
               3->listener?.goToKotlinLayoutFragment()
               4->listener?.goToCommonGestureFragment()
               5->listener?.goToCustomGestureFragment()
               6->listener?.goToTabLayoutFragment()
               7->listener?.goToCardDemoFragment()
               8->listener?.goToCoroutineDemoFragment()
               9->listener?.goToFlowDemoFragment()
               10->listener?.goToSharedFlowDemoFragment()
               11->listener?.goToSqlDemoFragment()
               12->listener?.goToRoomDemoFragment()
               13->listener?.goToVideoPlayBackFragment()
               14->listener?.goToPermissionDemoFragment()
            }
            binding.spinner.setSelection(0)
         }

         override fun onNothingSelected(parent: AdapterView<*>?) {
         }
      }
      val intentToSecondActivity = View.OnClickListener { _ ->
         listener?.intentToSecondActivity()
      }
      binding.fab.setOnClickListener { _ ->
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Intent to SecondActivity",intentToSecondActivity)
            .setAnchorView(R.id.fab).show()
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