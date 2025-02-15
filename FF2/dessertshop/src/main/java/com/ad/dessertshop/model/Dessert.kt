package com.ad.dessertshop.model

import android.os.Parcel
import android.os.Parcelable

data class Dessert(val imageId: Int, val price: Int, val startProductionAmount: Int) : Parcelable {
   constructor(parcel: Parcel) : this(
      parcel.readInt(),
      parcel.readInt(),
      parcel.readInt()
   ) {
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeInt(imageId)
      parcel.writeInt(price)
      parcel.writeInt(startProductionAmount)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<Dessert> {

      override fun createFromParcel(parcel: Parcel): Dessert {
         return Dessert(parcel)
      }

      override fun newArray(size: Int): Array<Dessert?> {
         return arrayOfNulls(size)
      }
   }
}
