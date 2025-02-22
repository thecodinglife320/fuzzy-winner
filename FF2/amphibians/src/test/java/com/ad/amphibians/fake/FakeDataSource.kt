package com.ad.amphibians.fake

import com.ad.amphibians.model.Amphibians

object FakeDataSource {

   val amphibiansList
      get() = listOf(
         Amphibians(
            name = "",
            type = "",
            description = "",
            img_src = ""
         ),
         Amphibians(
            name = "",
            type = "",
            description = "",
            img_src = ""
         )
      )
}