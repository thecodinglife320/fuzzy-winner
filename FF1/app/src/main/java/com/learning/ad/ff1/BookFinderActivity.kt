package com.learning.ad.ff1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.ad.ff1.databinding.ActivityBookFinderBinding
import com.learning.ad.ff1.databinding.BookCardViewBinding
import com.learning.ad.ff1.databinding.FragmentResultBinding
import com.learning.ad.ff1.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch

class BookFinderActivity : AppCompatActivity() {
   private lateinit var binding: ActivityBookFinderBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityBookFinderBinding.inflate(layoutInflater)
      setContentView(binding.root)
      setSupportActionBar(binding.toolbar)
      val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav_book_finder) as NavHostFragment
      val navController = navHostFragment.navController
      val appBarConfiguration = AppBarConfiguration(navController.graph)
      NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
      NavigationUI.setupWithNavController(binding.toolbar,navController)
   }
}
class ResultFragment : Fragment() {
   private var _binding: FragmentResultBinding? = null
   private val binding get() = _binding!!
   private val viewModel: BookViewModel by activityViewModels()
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
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

   private var _binding: FragmentSearchBinding? = null
   private val binding get() = _binding!!
   private val viewModel: BookViewModel by activityViewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSearchBinding.inflate(inflater, container, false)
      binding.lifecycleOwner = viewLifecycleOwner
      binding.viewModel = viewModel
      viewModel.status.observe(viewLifecycleOwner) {
         if (it == BookApiStatus.DONE) findNavController().navigate(R.id.action_searchFragment_to_resultFragment)
      }
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.searchView.setOnQueryTextListener(this)
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   override fun onQueryTextSubmit(query: String?): Boolean {
      if (!query.isNullOrBlank()){
         viewModel.searchBooks(query)
         return true
      }else{
         Toast.makeText(requireContext(), "Please enter book name", Toast.LENGTH_SHORT).show()
         return false
      }
   }
   override fun onQueryTextChange(newText: String?): Boolean {
      return false
   }
}
class BookAdapter(val searchOnWeb: (Book) -> Unit): ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallBack()){
   class BookDiffCallBack: DiffUtil.ItemCallback<Book>(){
      override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.volumeInfo.infoLink == newItem.volumeInfo.infoLink
      override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem ==newItem
   }
   class BookViewHolder(val binding: BookCardViewBinding): ViewHolder(binding.root){
      fun bind(book: Book){
         binding.book = book
         binding.executePendingBindings()
      }
   }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder = BookViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_card_view,parent,false))
   override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
      val book = getItem(position)
      holder.bind(book)
      holder.itemView.setOnClickListener { searchOnWeb(book) }
   }
}
class BookViewModel : ViewModel(){
   private val _status = MutableLiveData(BookApiStatus.EMPTY)
   val status: LiveData<BookApiStatus> = _status

   private val _books = MutableLiveData<List<Book>>()
   val books: LiveData<List<Book>> = _books

   private val _totalItems = MutableLiveData<String>()
   val totalItem: LiveData<String> = _totalItems
   fun searchBooks(query: String) {
      _status.value = BookApiStatus.LOADING
      viewModelScope.launch {
         try {
            val response = BookFinderApi.retrofitService.getDetails(
               searchString = query,
               maxResults = "10",
               printType = "books"
            )
            _totalItems.value = "10 out of ${response.totalItems} books found."
            _books.value = response.items
            _status.value = BookApiStatus.DONE
            _books.value!!.forEach {
               Log.d(TAG, it.volumeInfo.title)
            }
         }
         catch (e: Exception) {
            _status.value = BookApiStatus.ERROR
            Log.d(TAG1, e.message.toString())
         }
      }
   }
   companion object{
      const val TAG = "BookViewModel"
      const val TAG1 = "error"
   }
   fun resetStatus() {
      _status.value = BookApiStatus.EMPTY
   }
}
enum class BookApiStatus { LOADING, ERROR, DONE, EMPTY }