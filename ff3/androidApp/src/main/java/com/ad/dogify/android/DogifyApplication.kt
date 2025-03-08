package com.ad.dogify.android

import android.app.Application
import com.ad.dogify.di.initKoin

class DogifyApplication: Application()  {
   override fun onCreate() {
      super.onCreate()
      initKoin()
   }
}