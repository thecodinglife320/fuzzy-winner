package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto

object FakeDataSource {

   private const val idOne = "img1"
   private val idTwo = "img2"
   private val imgOne = "url.1"
   private const val imgTwo = "url.2"

   val photosList
      get() = listOf(
         MarsPhoto(
            id = idOne,
            img_src = imgOne,
         ),
         MarsPhoto(
            id = idTwo,
            img_src = imgTwo
         )
      )
}