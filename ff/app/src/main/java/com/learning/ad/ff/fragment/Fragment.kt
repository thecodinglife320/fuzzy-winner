package com.learning.ad.ff.fragment

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.core.widget.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import com.google.android.material.slider.*
import com.google.android.material.snackbar.*
import com.learning.ad.ff.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.viewmodel.*
import kotlinx.coroutines.*

class FirstFragment : Fragment(){
   private var _binding: FragmentFirstBinding? = null
   private val binding get() = _binding!!
   private var seekValue = 10
   private var listener: FirstFragmentListener? = null
   interface FirstFragmentListener{
      fun onButtonClick(fontSize: Int, text: String)
      fun showTime():String?
      fun sendMessage()
   }

   override fun onAttach(context: Context) {
      super.onAttach(context)
      try {
         listener = context as FirstFragmentListener
      }catch (e: ClassCastException){
         throw ClassCastException("$context must implement FirstFragmentListener")
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.seekBar.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
         override fun onStartTrackingTouch(slider: Slider) {}
         override fun onStopTrackingTouch(slider: Slider) {}
      })
      binding.seekBar.addOnChangeListener { _, value, _ ->
         println(value)
         seekValue = value.toInt()
      }
      binding.changeTextBtn.setOnClickListener {
         listener?.onButtonClick(seekValue,binding.textEdt.text.toString())
      }
      binding.getTimeFromServiceBtn.setOnClickListener {
         binding.textEdt.setText(listener?.showTime().toString())
      }
      binding.sendMessageToService.setOnClickListener { listener?.sendMessage() }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class SecondFragment : Fragment() {
   private var _binding: FragmentSecondBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   fun changeTextProperties(fontSize: Int, text: String) {
      binding.textTv.textSize = fontSize.toFloat()
      binding.textTv.text = text
   }
}
class MainFragment : Fragment() {
   private lateinit var items: Array<String>
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
      items = resources.getStringArray(R.array.fragments)
      val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
      (binding.spinner as? AutoCompleteTextView)?.setAdapter(adapter)
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.userNameEdt.doOnTextChanged { text, _, _, _ ->
         viewModel.updateUserInput(text.toString())
      }
      binding.spinner.onItemClickListener =
         AdapterView.OnItemClickListener { parent, _, position, _ ->
            when (parent.adapter.getItem(position).toString()) {
               items[0] -> listener?.goToMotionEventFragment()
               items[1] -> listener?.goToMotionLayoutFragment()
               items[2] -> listener?.goToKotlinLayoutFragment()
               items[3] -> listener?.goToCommonGestureFragment()
               items[4] -> listener?.goToCustomGestureFragment()
               items[5] -> listener?.goToTabLayoutFragment()
               items[6] -> listener?.goToCardDemoFragment()
               items[7] -> listener?.goToCoroutineDemoFragment()
               items[8] -> listener?.goToFlowDemoFragment()
               items[9] -> listener?.goToSharedFlowDemoFragment()
               items[10] -> listener?.goToSqlDemoFragment()
               items[11] -> listener?.goToRoomDemoFragment()
               items[12] -> listener?.goToVideoPlayBackFragment()
               items[13] -> listener?.goToPermissionDemoFragment()
            }
         }
      val intentToSecondActivity = View.OnClickListener { _ ->
         listener?.intentToSecondActivity()
      }
      binding.secondBtn.setOnClickListener { _ ->
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Intent to SecondActivity",intentToSecondActivity)
            .setAnchorView(R.id.second_btn).show()
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