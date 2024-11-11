package com.learning.ad.ff.lifecycleowner

import androidx.lifecycle.*
import com.learning.ad.ff.observer.*

class MainActivityLOwner : LifecycleOwner {
   private val lifecycleRegistry = LifecycleRegistry(this)

   init {
      lifecycle.addObserver(MainActivityObserver())
   }

   override val lifecycle: Lifecycle
      get() = lifecycleRegistry

   fun start() { lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START) }

   fun stop() { lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP) }

   fun destroy() { lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY) }

   fun resume() { lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME) }
}