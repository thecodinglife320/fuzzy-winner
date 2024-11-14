package com.learning.ad.ff.fragment

import android.os.*
import android.util.*
import android.view.*
import android.widget.ScrollView
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.learning.ad.ff.*
import com.learning.ad.ff.adapter.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.model.*
import com.learning.ad.ff.observer.*

class CardDemoFragment : Fragment() {
   private var _binding:FragmentCardDemoBinding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentCardDemoBinding.inflate(inflater,container,false)
      val bugDroidDataArray = resources.getStringArray(R.array.android_bug_data)

      val bugDroids = ArrayList<BugDroid>()
      for (data in bugDroidDataArray) {
         val attributes = data.split("|")
         bugDroids.add(BugDroid(attributes[1],resources.getIdentifier(attributes[0], "drawable", requireContext().packageName),attributes[2]))
      }
      binding.androidBugRv.adapter = AndroidBugAdapter(bugDroids)
      binding.androidBugRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
      requireActivity().findViewById<ScrollView>(R.id.activity_sv).requestDisallowInterceptTouchEvent(true)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}