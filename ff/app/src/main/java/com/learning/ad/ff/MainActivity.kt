package com.learning.ad.ff

import android.app.*
import android.content.*
import android.os.*
import android.util.*
import androidx.activity.result.*
import androidx.activity.result.contract.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.fragment.*
import com.learning.ad.ff.fragment.FirstFragment.*
import com.learning.ad.ff.lifecycleowner.*
import com.learning.ad.ff.observer.*

class MainActivity : FragmentActivity(), FirstFragmentListener,MainFragment.MainFragmentListener {
   var myService: BoundService? = null
   var isBound = false
   private val myConnection = object : ServiceConnection{
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
         val binder = service as BoundService.MyLocalBinder
         myService = binder.getService()
         isBound = true
         Log.d(TAG, "$name successfully bind to service")
      }

      override fun onServiceDisconnected(name: ComponentName?) {
         isBound = false
      }

   }
   private lateinit var launcher: ActivityResultLauncher<Intent>
   private lateinit var lifecycleOwner: MainActivityLOwner
   private lateinit var binding: ActivityMainBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)
      lifecycleOwner = MainActivityLOwner()
      launcher = registerForActivityResult(
         ActivityResultContracts.StartActivityForResult()
      ) { result: ActivityResult ->
         if (result.resultCode == Activity.RESULT_OK)
         // Handle the result data
            result.data?.extras?.getString("return")?.let {
               Log.d(TAG, it)
            }
      }
      val intent = Intent(this, BoundService::class.java)
      bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
   }

   override fun onButtonClick(fontSize: Int, text: String) { (supportFragmentManager.findFragmentById(R.id.second_fragment) as SecondFragment).changeTextProperties(fontSize, text) }
   override fun showTime(): String? = myService?.getCurrentTime()

   override fun goToMotionEventFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToMotionEventFragment())
   override fun goToMotionLayoutFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToMotionLayoutFragment())
   override fun goToKotlinLayoutFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToKotlinLayoutFragment())
   override fun goToCommonGestureFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToCommonGesturesFragment())
   override fun goToCustomGestureFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToCustomGestureFragment())
   override fun goToTabLayoutFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToTabLayoutFragment())
   override fun goToCardDemoFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToCardDemoFragment())
   override fun intentToSecondActivity() {
      val intent = Intent(this,SecondActivity::class.java)
      //intent.putExtra("qString","Hello SecondFragment")
      //launcher.launch(intent)
      startActivity(intent)
   }
   override fun goToCoroutineDemoFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToCoroutineDemoFragment())
}