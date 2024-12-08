package com.learning.ad.ff.fragment

import android.annotation.*
import android.app.*
import android.app.PendingIntent.*
import android.content.*
import android.graphics.drawable.*
import android.net.*
import android.os.*
import android.util.*
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.learning.ad.ff.*
import com.learning.ad.ff.databinding.*

class VideoPlayBackFragment : Fragment() {
   private var _binding:FragmentVideoPlayBackBinding? = null
   private val binding get() = _binding!!
   private var receiver: BroadcastReceiver? = null
   private val requestCode = 101
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentVideoPlayBackBinding.inflate(layoutInflater,container,false)
      return  binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val mediaController = MediaController(requireContext())
      binding.videoView.setMediaController(mediaController)
      mediaController.setAnchorView(binding.videoView)
      val videoUri= Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.demo)
      binding.videoView.setVideoURI(videoUri)
      binding.videoView.start()
      binding.textView10.text = getString(R.string.title_video)
      binding.button3.setOnClickListener {
         val params = PictureInPictureParams.Builder()
            .setAspectRatio(Rational(16, 9))
            .build()
         requireActivity().enterPictureInPictureMode(params)
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   @SuppressLint("InlinedApi")
   override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
      super.onPictureInPictureModeChanged(isInPictureInPictureMode)
      if (isInPictureInPictureMode){
         Toast.makeText(requireContext(), "PIP mode", Toast.LENGTH_SHORT).show()
         val filter = IntentFilter()
         filter.addAction(requireContext().packageName+".VIDEO_INFO")
         receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
               Toast.makeText(requireContext(), "Playing Forbidden Color", Toast.LENGTH_SHORT).show()
            }
         }
         requireContext().registerReceiver(receiver, filter,Context.RECEIVER_EXPORTED)
         createPipAction()
      }else requireContext().unregisterReceiver(receiver)
   }
   private fun createPipAction() {
      val actionIntent = Intent(requireContext().packageName+".VIDEO_INFO")
      val pendingIntent = getBroadcast(requireContext(), requestCode, actionIntent, FLAG_IMMUTABLE)
      val actions = ArrayList<RemoteAction>()
      val icon = Icon.createWithResource(requireContext(), R.drawable.baseline_info_24)
      val remoteAction = RemoteAction(icon, "Info", "Video Info", pendingIntent)
      actions.add(remoteAction)
      val params = PictureInPictureParams.Builder().setActions(actions).build()
      requireActivity().setPictureInPictureParams(params)
   }
}