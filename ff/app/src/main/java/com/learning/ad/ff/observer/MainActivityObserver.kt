package com.learning.ad.ff.observer

import android.util.Log
import androidx.lifecycle.*

const val TAG = "MainActivityObserver"

class MainActivityObserver : DefaultLifecycleObserver {

   override fun onCreate(owner: LifecycleOwner) { Log.d(TAG, "onCreate") }

   override fun onDestroy(owner: LifecycleOwner) { Log.d(TAG, "onDestroy") }

   override fun onPause(owner: LifecycleOwner) { Log.d(TAG, "onPause") }

   override fun onResume(owner: LifecycleOwner) { Log.d(TAG, "onResume") }

   override fun onStart(owner: LifecycleOwner) { Log.d(TAG, "onStart") }

   override fun onStop(owner: LifecycleOwner) { Log.d(TAG, "onStop") }
}