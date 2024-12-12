package com.learning.ad.ff1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MovieViewModel :ViewModel(){
   val ticketNumber= MutableLiveData(0)
   val date = MutableLiveData("")
   val time = MutableLiveData("")
   val seatLoc = MutableLiveData("")
   val totalMoney = MutableLiveData(0.00)
   val dateList = getDateOptions()
   val seatLocationList = getSeatLocations()
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