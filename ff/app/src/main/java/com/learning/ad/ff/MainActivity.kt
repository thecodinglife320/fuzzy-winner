package com.learning.ad.ff

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.learning.ad.ff.databinding.ActivityMainBinding
import com.learning.ad.ff.fragment.FirstFragment.FirstFragmentListener
import com.learning.ad.ff.fragment.MainFragment
import com.learning.ad.ff.fragment.MainFragmentDirections
import com.learning.ad.ff.fragment.SecondFragment
import com.learning.ad.ff.lifecycleowner.MainActivityLOwner
import com.learning.ad.ff.observer.TAG
import com.learning.ad.ff.service.BoundService
import com.learning.ad.ff.service.RemoteService

@Suppress("UNUSED_VARIABLE")
class MainActivity : AppCompatActivity(), FirstFragmentListener,MainFragment.MainFragmentListener {
    var myService: BoundService? = null
    var remoteService: Messenger?=null
    var isBound = false
    private val myConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            when(name?.shortClassName){
                ".service.RemoteService"->remoteService = Messenger(service)
                ".service.BoundService"->myService = (service as BoundService.MyLocalBinder).getService()
            }
            isBound = true
            Log.d(TAG, "${name?.shortClassName} successfully bind to activity")
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
        val intent1 = Intent(this, RemoteService::class.java)
        //bindService(intent1, myConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) unbindService(myConnection)
    }
    override fun onButtonClick(fontSize: Int, text: String) { (supportFragmentManager.findFragmentById(R.id.second_fragment) as SecondFragment).changeTextProperties(fontSize, text) }
    override fun showTime(): String? = myService?.getCurrentTime()
    override fun sendMessage() {
        if (!isBound) return
        val msg = Message.obtain()
        msg.data.putString("MyString", "Hello RemoteService")
        try {
            remoteService?.send(msg)
        }catch (e:RemoteException){
            e.printStackTrace()
        }
    }

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
    override fun goToFlowDemoFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToFlowDemoFragment())
    override fun goToSharedFlowDemoFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToSharedFlowDemoFragment())
    override fun goToSqlDemoFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToSqlDemoFragment())
    override fun intentToRetroAchievementActivity(userName: String) = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToRetroAchievementActivity(userName))
    override fun goToRoomDemoFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToRoomDemoFragment())
    override fun goToVideoPlayBackFragment() = findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToVideoPlayBackFragment())
    override fun goToPermissionDemoFragment() =findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToPermissionDemoFragment())
}