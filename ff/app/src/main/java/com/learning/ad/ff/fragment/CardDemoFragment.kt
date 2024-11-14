package com.learning.ad.ff.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import com.learning.ad.ff.*
import com.learning.ad.ff.adapter.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.model.*

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
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}