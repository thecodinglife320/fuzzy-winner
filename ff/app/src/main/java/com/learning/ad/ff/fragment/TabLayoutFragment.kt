package com.learning.ad.ff.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.*
import com.learning.ad.ff.adapter.*
import com.learning.ad.ff.databinding.FragmentTabLayoutBinding

class TabLayoutFragment : Fragment() {
    private var _binding: FragmentTabLayoutBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentTabLayoutBinding.inflate(inflater,container,false)
        repeat(4){
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }
        val adapter = TabPagerAdapter(requireActivity(), binding.tabLayout.tabCount)
        binding.viewPager.adapter= adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
        }.attach()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}