package com.learning.ad.ff1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.learning.ad.ff1.databinding.ActivityMovieBinding
import com.learning.ad.ff1.databinding.FragmentConfirmBinding
import com.learning.ad.ff1.databinding.FragmentDaySelectBinding
import com.learning.ad.ff1.databinding.FragmentIntroBinding
import com.learning.ad.ff1.databinding.FragmentPaymentBinding
import com.learning.ad.ff1.databinding.FragmentReceiptBinding
import com.learning.ad.ff1.databinding.FragmentSeatSelectBinding
import com.learning.ad.ff1.databinding.FragmentTimeSelectBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MovieActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMovieBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMovieBinding.inflate(layoutInflater)
      setContentView(binding.root)
      setSupportActionBar(binding.toolbar)

      val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav_movie_booking) as NavHostFragment
      val navController = navHostFragment.navController
      val appBarConfiguration = AppBarConfiguration(navController.graph)
      NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
      NavigationUI.setupWithNavController(binding.toolbar,navController)
   }
}
class ConfirmFragment : Fragment() {
   private var _binding: FragmentConfirmBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentConfirmBinding.inflate(layoutInflater, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.calculateTotal()
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.payToBookBtn.setOnClickListener {
         findNavController().navigate(R.id.action_confirmFragment_to_receiptFragment)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null

   }
}
class DaySelectFragment : Fragment() {
   private var _binding: FragmentDaySelectBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentDaySelectBinding.inflate(layoutInflater, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.date.observe(viewLifecycleOwner){
         Log.d(TAG1, viewModel.date.value.toString())
         when(it){
            viewModel.dateList[0]->binding.radioButton.isChecked = true
            viewModel.dateList[1]->binding.radioButton2.isChecked = true
            viewModel.dateList[2]->binding.radioButton3.isChecked = true
            viewModel.dateList[3]->binding.radioButton4.isChecked = true
            else ->{
               binding.radioButton.isChecked = true
               viewModel.date.value = viewModel.dateList[0]
            }
         }
      }
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.confirmDayBtn.setOnClickListener {
         findNavController().navigate(R.id.action_daySelectFragment_to_timeSelectFragment)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   companion object{
      const val TAG1 = "selectedDate"
   }
}
class IntroFragment : Fragment() {
   private var _binding: FragmentIntroBinding? = null
   private val viewModel: MovieViewModel by activityViewModels()
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
         override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            viewModel.ticketNumber.value = progress
         }
         override fun onStartTrackingTouch(seekBar: SeekBar?) {
         }
         override fun onStopTrackingTouch(seekBar: SeekBar?) {
         }
      })

      binding.buyBtn.setOnClickListener { findNavController().navigate(R.id.action_introFragment_to_seatSelectFragment) }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class PaymentFragment : Fragment() {
   private var _binding: FragmentPaymentBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.payConfirmBtn.setOnClickListener {
         findNavController().navigate(R.id.action_paymentFragment_to_confirmFragment)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class ReceiptFragment : Fragment() {
   private var _binding: FragmentReceiptBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentReceiptBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class SeatSelectFragment : Fragment() {
   private var _binding: FragmentSeatSelectBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSeatSelectBinding.inflate(layoutInflater,container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.seatLoc.observe(viewLifecycleOwner){
         Log.d(TAG1, it.toString())
         when(it){
            viewModel.seatLocationList[0]->binding.radioButton.isChecked = true
            viewModel.seatLocationList[1]->binding.radioButton2.isChecked = true
            viewModel.seatLocationList[2]->binding.radioButton3.isChecked = true
            viewModel.seatLocationList[3]->binding.radioButton4.isChecked = true
            else ->{
               binding.radioButton.isChecked = true
               viewModel.seatLoc.value = viewModel.seatLocationList[0]
            }
         }
      }

      return binding.root
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.confirmDayBtn.setOnClickListener { findNavController().navigate(R.id.action_seatSelectFragment_to_daySelectFragment) }
   }
   companion object{
      const val TAG1 = "selectedSeat"
   }
}
class TimeSelectFragment : Fragment() {
   private var _binding: FragmentTimeSelectBinding? = null
   private val binding get() = _binding!!
   private val viewModel: MovieViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTimeSelectBinding.inflate(layoutInflater,container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      viewModel.time.observe(viewLifecycleOwner){
         when(it){
            binding.radioButton.text->binding.radioButton.isChecked = true
            binding.radioButton2.text->binding.radioButton2.isChecked = true
            binding.radioButton3.text->binding.radioButton3.isChecked = true
            binding.radioButton4.text->binding.radioButton4.isChecked = true
            else ->{
               binding.radioButton.isChecked = true
               viewModel.time.value = "Morning"
            }
         }
      }
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.confirmDayBtn.setOnClickListener { findNavController().navigate(R.id.action_timeSelectFragment_to_paymentFragment) }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   companion object
}
class MovieViewModel : ViewModel(){
   val ticketNumber= MutableLiveData(0)
   val date = MutableLiveData("")
   val time = MutableLiveData("")
   val seatLoc = MutableLiveData("")
   val totalMoney = MutableLiveData(0.00)
   val dateList = getDateOptions()
   val seatLocationList = getSeatLocations()
   init {
      ticketNumber.value =1
   }
   private fun getDateOptions(): List<String> {
      val options = mutableListOf<String>()
      val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
      val calendar = Calendar.getInstance()
      repeat(4) {
         options.add(formatter.format(calendar.time))
         calendar.add(Calendar.DATE, 1)
      }
      return options
   }
   private fun getSeatLocations(): List<String> {
      val options = mutableListOf<String>()
      options.add("Front Row (100.00)")
      options.add("Middle Row (200.00)")
      options.add("Balcony (300.00)")
      options.add("Premium (600.00)")
      return options
   }
   fun calculateTotal() {
      val price: Double = when (seatLoc.value) {
         seatLocationList[0] -> 100.00
         seatLocationList[1] -> 200.00
         seatLocationList[2] -> 300.00
         seatLocationList[3] -> 600.00
         else -> 0.00
      }
      totalMoney.value = ticketNumber.value!!*price
   }
   fun setDate(selectedDate:String){
      date.value = selectedDate
   }
   fun setSeatLoc(selectedSeat:String){
      seatLoc.value = selectedSeat
   }
   fun setTime(time: String){
      this.time.value = time
   }
}