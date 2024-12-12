package com.learning.ad.ff1.bookfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.ad.ff1.R
import com.learning.ad.ff1.adapter.BookAdapter
import com.learning.ad.ff1.databinding.FragmentResultBinding
import com.learning.ad.ff1.viewmodel.BookViewModel

class ResultFragment : Fragment() {
   private var _binding:FragmentResultBinding? = null
   private val binding get() = _binding!!
   private val viewModel:BookViewModel by activityViewModels()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_result, container,false)
      binding.viewModel = viewModel
      binding.lifecycleOwner = viewLifecycleOwner
      binding.recyclerView.adapter = BookAdapter {book->
         openBrowser(book.volumeInfo.infoLink)
      }
      binding.recyclerView.layoutManager = LinearLayoutManager(context)
      viewModel.books.observe(viewLifecycleOwner){books->
         (binding.recyclerView.adapter as BookAdapter).submitList(books)
         viewModel.resetStatus()
      }
      return binding.root
   }
   private fun openBrowser(infoLink: String) {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(infoLink))
      startActivity(intent)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}