package com.learning.ad.ff.model

data class Customer(var id:Int, var name:String, var phoneNumber:String){
   constructor(name: String, phone: String) : this(0,name,phone) {
      this.name = name
      this.phoneNumber = phone
   }
}
