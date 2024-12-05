package com.learning.ad.ff.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.learning.ad.ff.R
import com.learning.ad.ff.databinding.FragmentVideoPlayBackBinding

class VideoPlayBackFragment : Fragment() {
    private var _binding:FragmentVideoPlayBackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayBackBinding.inflate(layoutInflater,container,false)
        binding.videoView.setVideoURI(Uri.parse("android.resource://${requireContext().packageName}/"+ R.raw.demo))
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}