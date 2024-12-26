package com.learning.ad.ff1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.learning.ad.ff1.databinding.ActivityInspireMeBinding
import com.learning.ad.ff1.databinding.FragmentAddBinding
import com.learning.ad.ff1.databinding.FragmentDisplayBinding
import com.learning.ad.ff1.databinding.FragmentLoginBinding
import com.learning.ad.ff1.databinding.FragmentRegisterBinding
import com.learning.ad.ff1.databinding.PostCardBinding
import kotlin.jvm.java

class InspireMeActivity : AppCompatActivity() {
   private lateinit var appBarConfiguration: AppBarConfiguration
   private lateinit var binding: ActivityInspireMeBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      WindowCompat.setDecorFitsSystemWindows(window, false)
      super.onCreate(savedInstanceState)

      binding = ActivityInspireMeBinding.inflate(layoutInflater)
      setContentView(binding.root)

      setSupportActionBar(binding.toolbar)
      val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
      val navController = navHostFragment.navController
      appBarConfiguration = AppBarConfiguration(navController.graph)
      setupActionBarWithNavController(navController, appBarConfiguration)
      binding.floatingActionButton.setOnClickListener {
         navController.navigate(R.id.action_displayFragment_to_addFragment)
      }
   }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      menuInflater.inflate(R.menu.menu_daynight, menu)
      return true
   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      return when (item.itemId) {
         R.id.action_logout -> {
            ViewModelProvider(this)[FirebaseViewModel::class.java].logOut()
            startActivity(Intent(this,AuthActivity::class.java))
            true
         }
         else -> super.onOptionsItemSelected(item)
      }
   }
   override fun onSupportNavigateUp(): Boolean {
      val navController = findNavController(R.id.nav_host_fragment_content_main)
      return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
   }
}
class DisplayFragment : Fragment() {
   private var _binding: FragmentDisplayBinding? = null
   private val binding get() = _binding!!
   private val db by lazy { Firebase.firestore }
   private val firebaseViewModel: FirebaseViewModel by activityViewModels { FirebaseViewModelFactory(Firebase.auth, db) }
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = FragmentDisplayBinding.inflate(inflater, container, false)
      binding.viewmodel = firebaseViewModel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      firebaseViewModel.getAllFirePosts()
      firebaseViewModel.postLoadingStatus.observe(viewLifecycleOwner) {
         if (it == PostLoadingStatus.IN_PROGRESS) {
            binding.progressBar.visibility = View.VISIBLE
         } else {
            if (it == PostLoadingStatus.SUCCESS) {
               binding.rvPostList.layoutManager = LinearLayoutManager(requireContext())
               val firePostAdapter = FirePostAdapter {firePost->
                  showPostDialog(firePost)
               }
               binding.rvPostList.adapter = firePostAdapter
               firePostAdapter.submitList(firebaseViewModel.posts.value)
            }
            binding.progressBar.visibility = View.GONE
         }
      }
   }
   private fun showPostDialog(it: FirePost) {
      val dialog = AlertDialog.Builder(requireContext())
      dialog.setTitle(it.title)
      dialog.setMessage(it.content)
      dialog.setPositiveButton("OK") { _, _ -> }
      dialog.show()
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class AddFragment : Fragment() {

   private var _binding: FragmentAddBinding? = null
   private val binding get() = _binding!!
   private val auth by lazy { Firebase.auth }
   private val fs by lazy { Firebase.firestore }
   private val firebaseViewModel: FirebaseViewModel by activityViewModels { FirebaseViewModelFactory(auth, fs) }
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = FragmentAddBinding.inflate(inflater, container, false)
      binding.viewmodel = firebaseViewModel
      binding.lifecycleOwner = viewLifecycleOwner
      firebaseViewModel.response.value=""
      return binding.root
   }
   @SuppressLint("SetTextI18n")
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      firebaseViewModel.postUploadStatus.observe(viewLifecycleOwner) {
         if (it == CloudUploadStatus.IN_PROGRESS) {
            binding.tvPostStatus.text = "uploading post to cloud"
         }else if (it == CloudUploadStatus.SUCCESS) {
            binding.editPostTitle.text?.clear()
            binding.editPostContent.text?.clear()
         }
      }
      binding.fabSave.setOnClickListener {
         firebaseViewModel.addNewPost(
            binding.editPostTitle.text.toString(),
            binding.editPostContent.text.toString())
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class LoginFragment : Fragment() {
   private var _binding: FragmentLoginBinding? = null
   val binding get() = _binding!!
   private val viewmodel: CloudAuthViewModel by activityViewModels { CloudAuthViewModelFactory(Firebase.auth) }
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = FragmentLoginBinding.inflate(inflater, container, false)
      binding.viewmodel = viewmodel
      binding.lifecycleOwner = viewLifecycleOwner
      viewmodel.checkUser()
      viewmodel.authState.observe(viewLifecycleOwner){
         if (it==AuthState.AUTHENTICATED){
            startActivity(Intent(requireContext(), InspireMeActivity::class.java))
            requireActivity().finish()
         }
      }
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.apply {
         btnLogin.setOnClickListener {
            viewmodel?.performLogin(
               editEmail.text.toString(),
               editPassword.text.toString()
            )
         }
         btnCreateAccount.setOnClickListener {
            viewmodel!!.authMsg.value = ""
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
         }
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class RegisterFragment : Fragment() {
   private var _binding: FragmentRegisterBinding? = null
   val binding get() = _binding!!
   private val viewmodel: CloudAuthViewModel by activityViewModels { CloudAuthViewModelFactory(Firebase.auth) }
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = FragmentRegisterBinding.inflate(inflater, container, false)
      binding.viewmodel = viewmodel
      binding.lifecycleOwner = viewLifecycleOwner
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      viewmodel.authState.observe(viewLifecycleOwner) {
         if (it == AuthState.AUTHENTICATED) {
            println("a")
            startActivity(Intent(requireContext(), InspireMeActivity::class.java))
            requireActivity().finish()
         }
      }
      binding.apply {
         btnRegister.setOnClickListener {
            viewmodel?.performRegistration(
               editUsername.text.toString(),
               editRegEmail.text.toString(),
               editRegPassword.text.toString(),
            )
         }
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
enum class CloudUploadStatus { SUCCESS, FAILURE, IN_PROGRESS, NONE }
enum class PostLoadingStatus { SUCCESS, FAILURE, IN_PROGRESS, NONE }
class FirebaseViewModel(private val auth: FirebaseAuth, private val db: FirebaseFirestore) : ViewModel() {
   val postUploadStatus  = MutableLiveData(CloudUploadStatus.NONE)
   val postLoadingStatus =MutableLiveData(PostLoadingStatus.NONE)
   val response =MutableLiveData<String>()
   val posts = MutableLiveData<List<FirePost>>()
   private fun loggedInUser() = auth.currentUser
   fun getAllFirePosts(): Task<QuerySnapshot> {
      postUploadStatus.value = CloudUploadStatus.IN_PROGRESS
      return db.collection("posts").get()
         .addOnFailureListener { queryFailure(it.message) }
         .addOnSuccessListener {
            posts.value = it.toObjects(FirePost::class.java)
            postLoadingStatus.value = PostLoadingStatus.SUCCESS
            response.value = "Successfully loaded posts"
         }
   }

   private fun saveFirePost(firePost: FirePost) {
      postUploadStatus.value = CloudUploadStatus.IN_PROGRESS
      db.collection("posts").add(firePost)
         .addOnFailureListener {
            queryFailure(it.message) }
         .addOnSuccessListener {
            postUploadStatus.value = CloudUploadStatus.SUCCESS
            response.value = "Successfully added post"
      }
   }
   private fun queryFailure(message: String?) {
      postLoadingStatus.value = PostLoadingStatus.FAILURE
      postUploadStatus.value = CloudUploadStatus.FAILURE
      response.value = message.toString()
   }
   fun addNewPost(subject: String, content: String) {
      if (subject.isNotEmpty() && content.isNotEmpty() && loggedInUser() != null) saveFirePost(FirePost(subject, content, timestamp = System.currentTimeMillis(), name = loggedInUser()?.displayName!!,userId = loggedInUser()?.uid!!))
      else queryFailure("Please enter valid title and description")
   }
   fun logOut() = auth.signOut()
}

class FirebaseViewModelFactory(
   private val auth: FirebaseAuth, private val db: FirebaseFirestore
) : ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(FirebaseViewModel::class.java)) {
         @Suppress("UNCHECKED_CAST") return FirebaseViewModel(auth, db) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class")
   }
}
enum class AuthState {
   AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
}
class CloudAuthViewModel(private val auth: FirebaseAuth) : ViewModel() {
   val authState =MutableLiveData(AuthState.UNAUTHENTICATED)
   val authMsg =MutableLiveData("")
   private fun firebaseSignIn(email: String, password: String) =
      auth.signInWithEmailAndPassword(email, password)
         .addOnFailureListener { invalidAuth(it.message) }
         .addOnSuccessListener { authState.value = AuthState.AUTHENTICATED }
   private fun firebaseSignUp(username: String, email: String, password: String) =
      auth.createUserWithEmailAndPassword(email, password)
         .addOnSuccessListener {
            authState.value = AuthState.AUTHENTICATED
            it.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(username).build())
         }
         .addOnFailureListener { invalidAuth(it.message) }
   private fun invalidAuth(message: String?) {
      authState.value = AuthState.INVALID_AUTHENTICATION
      authMsg.value = message.toString()
   }

   // methods for fragment to call
   fun performLogin(email: String, password: String) {
      if (email.isNotEmpty() && password.isNotEmpty()) firebaseSignIn(email, password)
      else invalidAuth("Please enter valid email and password")
   }
   fun performRegistration(username: String, email: String, password: String) {
      if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) firebaseSignUp(username, email, password)
      else invalidAuth("Please enter valid username, email and password")
   }
   fun checkUser() {
      if (auth.currentUser!=null) authState.value = AuthState.AUTHENTICATED
      else authState.value = AuthState.UNAUTHENTICATED
   }
}

class CloudAuthViewModelFactory(private val auth: FirebaseAuth) : ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(CloudAuthViewModel::class.java)) {
         @Suppress("UNCHECKED_CAST")
         return CloudAuthViewModel(auth) as T
      }
      throw IllegalArgumentException("Unable to construct cloud auth viewmodel")
   }
}
class FirePostAdapter(private val onPostClick: (FirePost) -> Unit) : ListAdapter<FirePost, FirePostAdapter.FirePostViewHolder>(PostDiffCallback()) {
   class FirePostViewHolder(
      private val binding: PostCardBinding
   ) : RecyclerView.ViewHolder(binding.root) {
      fun bind(firePost: FirePost, onPostClick: (FirePost) -> Unit) {
         binding.post = firePost
         binding.root.setOnClickListener { onPostClick(firePost) }
         binding.executePendingBindings()
      }
   }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirePostViewHolder {
      val postCardBinding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return FirePostViewHolder(postCardBinding)
   }
   override fun onBindViewHolder(holder: FirePostViewHolder, position: Int) {
      val firePost = getItem(position)
      holder.bind(firePost, onPostClick)
   }
   class PostDiffCallback : DiffUtil.ItemCallback<FirePost>() {
      override fun areItemsTheSame(oldItem: FirePost, newItem: FirePost): Boolean {
         return oldItem.title == newItem.title
      }
      override fun areContentsTheSame(oldItem: FirePost, newItem: FirePost): Boolean {
         return oldItem == newItem
      }
   }
}
data class FirePost(
   val title: String = "", // title
   val content: String = "", // body
   val timestamp: Long = 0L, // date and time
   val name: String = "", // author
   val userId: String = "", // unique id from Firebase
)
class AuthActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_auth)
   }
}