package com.learning.ad.ff.fragment

import android.*
import android.content.*
import android.content.pm.*
import android.media.*
import android.net.*
import android.os.*
import android.provider.*
import android.util.*
import android.view.*
import androidx.appcompat.app.*
import androidx.core.app.*
import androidx.core.content.*
import androidx.fragment.app.*
import com.learning.ad.ff.R
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.observer.*
import java.io.*

class PermissionDemoFragment : Fragment() {
   private var _binding:FragmentPermissionDemoBinding? = null
   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentPermissionDemoBinding.inflate(layoutInflater,container,false)
      return binding.root
   }
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setupPermissions()
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   private fun makeRequest() = ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_REQUEST_CODE)
   private fun setupPermissions() {
      val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
      if (permission != PackageManager.PERMISSION_GRANTED) {
         if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.RECORD_AUDIO)) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Permission to access the microphone is required for this app to record audio.")
                 .setTitle("Permission required")
                 .setPositiveButton("OK") { _, _ -> makeRequest() }
            val dialog = builder.create()
            dialog.show()
         }
         else makeRequest()
      }
   }
   @Deprecated("Deprecated in Java")
   override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
      if (requestCode == RECORD_REQUEST_CODE) {
         if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) Log.i(TAG, "Permission has been granted by user")
         else Log.i(TAG, "Permission has been denied by user")
      }
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      //start recording
      binding.recordBtn.setOnClickListener { recordAudio() }
   }
   private fun recordAudio() {
      val outputFile = Environment.getExternalStorageDirectory().absolutePath + "/recording.3gp"

      val mediaRecorder = MediaRecorder().apply {
         try {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
         } catch (e: Exception) {
            //navigate user to setting
            showPermissionDialog()
         }
         try {
            prepare()
            start()
         } catch (e: IOException) {}
         catch (e: IllegalStateException) {}
      }
   }
   private fun showPermissionDialog() {
      AlertDialog.Builder(requireContext())
         .setTitle(getString(R.string.attemp_to_request))
         .setMessage(getString(R.string.notify_permission))
         .setPositiveButton("Đi tới Cài đặt") { _, _ ->
            // Mở Cài đặt ứng dụng
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
               data = Uri.fromParts("package", requireActivity().packageName, null)
            }
            startActivity(intent)
         }
         .setNegativeButton("Hủy") { dialog, _ ->
            dialog.dismiss()
         }
         .setCancelable(false)
         .show()
   }

   companion object{
      private const val RECORD_REQUEST_CODE = 101
   }
}