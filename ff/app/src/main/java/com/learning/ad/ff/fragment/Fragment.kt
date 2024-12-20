@file:Suppress("DEPRECATION")
@file:SuppressLint("SetTextI18n")
package com.learning.ad.ff.fragment

import android.annotation.*
import android.content.*
import android.gesture.*
import android.os.*
import android.util.*
import android.view.*
import android.view.GestureDetector.*
import android.view.MotionEvent.*
import android.view.ScaleGestureDetector.*
import android.widget.*
import android.widget.SeekBar.*
import androidx.constraintlayout.widget.*
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.core.view.*
import androidx.core.widget.*
import androidx.databinding.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.recyclerview.widget.*
import com.google.android.material.slider.*
import com.google.android.material.snackbar.*
import com.google.android.material.tabs.*
import com.learning.ad.ff.R
import com.learning.ad.ff.adapter.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.db.CryCafeDB.Companion.COLUMN_CUSTOMER_NAME
import com.learning.ad.ff.db.CryCafeDB.Companion.COLUMN_CUSTOMER_PHONE
import com.learning.ad.ff.db.CryCafeDB.Companion.COLUMN_ID
import com.learning.ad.ff.model.*
import com.learning.ad.ff.provider.*
import com.learning.ad.ff.viewmodel.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlin.math.*
import kotlin.system.*

class FirstFragment : Fragment(){
   private var _binding: FragmentFirstBinding? = null
   private val binding get() = _binding!!
   private var seekValue = 10
   private var listener: FirstFragmentListener? = null
   interface FirstFragmentListener{
      fun onButtonClick(fontSize: Int, text: String)
      fun showTime():String?
      fun sendMessage()
   }

   override fun onAttach(context: Context) {
      super.onAttach(context)
      try {
         listener = context as FirstFragmentListener
      }catch (e: ClassCastException){
         throw ClassCastException("$context must implement FirstFragmentListener")
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.seekBar.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
         override fun onStartTrackingTouch(slider: Slider) {}
         override fun onStopTrackingTouch(slider: Slider) {}
      })
      binding.seekBar.addOnChangeListener { _, value, _ ->
         println(value)
         seekValue = value.toInt()
      }
      binding.changeTextBtn.setOnClickListener {
         listener?.onButtonClick(seekValue,binding.textEdt.text.toString())
      }
      binding.getTimeFromServiceBtn.setOnClickListener {
         binding.textEdt.setText(listener?.showTime().toString())
      }
      binding.sendMessageToService.setOnClickListener { listener?.sendMessage() }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class SecondFragment : Fragment() {
   private var _binding: FragmentSecondBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   fun changeTextProperties(fontSize: Int, text: String) {
      binding.textTv.textSize = fontSize.toFloat()
      binding.textTv.text = text
   }
}
class MainFragment : Fragment() {
   private lateinit var items: Array<String>
   private var _binding: FragmentMainBinding? = null
   private val binding get() = _binding!!
   private var listener: MainFragmentListener? = null
   private lateinit var viewModel: MainViewModel
   interface MainFragmentListener{
      fun goToMotionEventFragment()
      fun goToMotionLayoutFragment()
      fun goToKotlinLayoutFragment()
      fun goToCommonGestureFragment()
      fun goToCustomGestureFragment()
      fun goToTabLayoutFragment()
      fun goToCardDemoFragment()
      fun intentToSecondActivity()
      fun goToCoroutineDemoFragment()
      fun goToFlowDemoFragment()
      fun goToSharedFlowDemoFragment()
      fun goToSqlDemoFragment()
      fun intentToRetroAchievementActivity(userName:String)
      fun goToRoomDemoFragment()
      fun goToVideoPlayBackFragment()
      fun goToPermissionDemoFragment()
   }
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[MainViewModel::class.java]
      //collect flow
      lifecycleScope.launch {
         viewModel.userInput.collect{
            if (it == "longplay123") listener?.intentToRetroAchievementActivity(it)
         }
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding=FragmentMainBinding.inflate(inflater,container,false)
      items = resources.getStringArray(R.array.fragments)
      val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
      (binding.spinner as? AutoCompleteTextView)?.setAdapter(adapter)
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.userNameEdt.doOnTextChanged { text, _, _, _ ->
         viewModel.updateUserInput(text.toString())
      }
      binding.spinner.onItemClickListener =
         AdapterView.OnItemClickListener { parent, _, position, _ ->
            when (parent.adapter.getItem(position).toString()) {
               items[0] -> listener?.goToMotionEventFragment()
               items[1] -> listener?.goToMotionLayoutFragment()
               items[2] -> listener?.goToKotlinLayoutFragment()
               items[3] -> listener?.goToCommonGestureFragment()
               items[4] -> listener?.goToCustomGestureFragment()
               items[5] -> listener?.goToTabLayoutFragment()
               items[6] -> listener?.goToCardDemoFragment()
               items[7] -> listener?.goToCoroutineDemoFragment()
               items[8] -> listener?.goToFlowDemoFragment()
               items[9] -> listener?.goToSharedFlowDemoFragment()
               items[10] -> listener?.goToSqlDemoFragment()
               items[11] -> listener?.goToRoomDemoFragment()
               items[12] -> listener?.goToVideoPlayBackFragment()
               items[13] -> listener?.goToPermissionDemoFragment()
            }
         }
      val intentToSecondActivity = View.OnClickListener { _ ->
         listener?.intentToSecondActivity()
      }
      binding.secondBtn.setOnClickListener { _ ->
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Intent to SecondActivity",intentToSecondActivity)
            .setAnchorView(R.id.second_btn).show()
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   override fun onAttach(context: Context) {
      super.onAttach(context)
      try {
         listener = context as MainFragmentListener
      }catch (e: ClassCastException){
         throw ClassCastException("$context must implement MainFragmentListener")
      }
   }
   override fun onDetach() {
      super.onDetach()
      listener=null
   }
}
class ConvertCurrencyFragment : Fragment() {
   private lateinit var binding: FragmentConvertCurrencyBinding
   private lateinit var viewModel: ConvertCurrencyViewModel
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity?.application?.let {
         val factory = SavedStateViewModelFactory(it, this)
         viewModel = ViewModelProvider(this, factory)[ConvertCurrencyViewModel::class.java]
      }
   }
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_convert_currency,container,false)
      binding.lifecycleOwner=this
      binding.mViewModel = viewModel
      return binding.root
   }
}
class MotionEventFragment : Fragment() {
   private var _binding: FragmentMotionEventBinding? = null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentMotionEventBinding.inflate(inflater,container,false)
      return binding.root
   }
   @SuppressLint("ClickableViewAccessibility")
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      binding.root.setOnTouchListener {_,m: MotionEvent ->
         requireActivity().findViewById<ScrollView>(R.id.activity_sv).requestDisallowInterceptTouchEvent(true)
         handleTouch(m)
         true
      }
      //Log.d(TAG,"onViewCreated")
   }
   private fun handleTouch(m: MotionEvent) {
      for (i in 0 until m.pointerCount){
         val x = m.getX(i)
         val y = m.getY(i)
         val id = m.getPointerId(i)
         val action = m.actionMasked
         val actionIndex = m.actionIndex
         val actionStr:String = when(action){
            ACTION_DOWN -> "DOWN"
            ACTION_UP ->"UP"
            ACTION_POINTER_DOWN ->"POINTER DOWN"
            ACTION_POINTER_UP ->"POINTER UP"
            ACTION_MOVE ->"MOVE"
            else -> ""
         }
         val touchStatus= "Action: $actionStr \n"+
           "Index action:$actionIndex \n" +
           "ID pointer: $id \n" +
           "X: $x \n" +
           "Y: $y \n"
         if ((id==0)) binding.textView.text=touchStatus
         else binding.textView2.text=touchStatus
      }
   }
}
class MotionLayoutFragment : Fragment() {
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      return inflater.inflate(R.layout.fragment_motion_layout, container, false)
   }
}
class KotlinLayoutFragment : Fragment() {
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      super.onCreate(savedInstanceState)
      return configureLayout()
   }

   private fun configureLayout():View {
      val myButton = Button(context)
      val myLayout = ConstraintLayout(requireContext())
      val set = ConstraintSet()
      val myEdt = EditText(context)
      myEdt.id = R.id.myEditText
      myEdt.width= 100.convertToPx()
      myButton.id = R.id.myButton

      myLayout.addView(myButton)
      myLayout.addView(myEdt)

      set.constrainWidth(myButton.id, WRAP_CONTENT)
      set.constrainHeight(myButton.id, WRAP_CONTENT)
      set.connect(myButton.id, START, PARENT_ID, START)
      set.connect(myButton.id, END, PARENT_ID, END)
      set.connect(myButton.id, TOP, PARENT_ID, TOP)
      set.connect(myButton.id, BOTTOM, PARENT_ID, BOTTOM)

      set.constrainWidth(myEdt.id, WRAP_CONTENT)
      set.constrainHeight(myEdt.id, WRAP_CONTENT)
      set.connect(myEdt.id, START, PARENT_ID, START)
      set.connect(myEdt.id, END, PARENT_ID, END)
      set.connect(myEdt.id, BOTTOM, myButton.id, TOP)
      set.applyTo(myLayout)
      return myLayout
   }
   private fun Int.convertToPx(): Int {
      val r = resources
      return TypedValue.applyDimension(
         TypedValue.COMPLEX_UNIT_DIP,
         toFloat(),
         r.displayMetrics
      ).toInt()
   }
}
class CommonGesturesFragment : Fragment(), OnGestureListener, OnDoubleTapListener {
   private var _binding:FragmentCommonGesturesBinding?=null
   private val binding get() = _binding!!
   private var gDetector: GestureDetectorCompat? = null
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      super.onCreate(savedInstanceState)
      _binding = FragmentCommonGesturesBinding.inflate(inflater,container,false)
      return binding.root
   }

   @SuppressLint("ClickableViewAccessibility")
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      this.gDetector = GestureDetectorCompat(requireContext(), this)
      gDetector?.setOnDoubleTapListener(this)
      binding.root.setOnTouchListener { _, event ->
         gDetector?.onTouchEvent(event)
         true
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   override fun onDown(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text="onDown"
      return true
   }
   override fun onShowPress(e: MotionEvent) {
      binding.gestureStatusTv.text = "onShowPress"
   }
   override fun onSingleTapUp(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onSingleTapUp"
      return true
   }

   override fun onScroll(
      e1: MotionEvent?,
      e2: MotionEvent,
      distanceX: Float,
      distanceY: Float
   ): Boolean {
      binding.gestureStatusTv.text = "onScroll"
      return true
   }

   override fun onLongPress(e: MotionEvent) {
      binding.gestureStatusTv.text = "onLongPress"
   }

   override fun onFling(
      e1: MotionEvent?,
      e2: MotionEvent,
      velocityX: Float,
      velocityY: Float
   ): Boolean {
      binding.gestureStatusTv.text = "onFling"
      return true
   }

   override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onSingleTapConfirmed"
      return true
   }

   override fun onDoubleTap(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onDoubleTap"
      return true
   }

   override fun onDoubleTapEvent(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onDoubleTapEvent"
      return true
   }
}
class CustomGestureFragment : Fragment() {
   private var scaleFactor =1f
   private val matrix = android.graphics.Matrix()
   private var _binding:FragmentCustomGestureBinding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentCustomGestureBinding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }

   @SuppressLint("ClickableViewAccessibility")
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val scaleGestureDetector = ScaleGestureDetector(requireContext(), object : SimpleOnScaleGestureListener() {
         override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = max(0.1f, scaleFactor.coerceAtMost(5.0f))
            matrix.setScale(scaleFactor, scaleFactor,binding.imageView2.width/2f,binding.imageView2.height/2f)
            binding.imageView2.imageMatrix = matrix
            return true
         }

         override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            // Called when a scale gesture begins
            return true
         }

         override fun onScaleEnd(detector: ScaleGestureDetector) {
            // Called when a scale gesture ends
         }
      })
      binding.imageView2.setOnTouchListener {_,m: MotionEvent ->
         scaleGestureDetector.onTouchEvent(m)
         true
      }
      val gestureLibrary = GestureLibraries.fromRawResource(requireContext(), R.raw.gestures)
      gestureLibrary.load()
      binding.gOverlay.addOnGesturePerformedListener{ _, gesture ->
         val predictions = gestureLibrary.recognize(gesture)
         if (predictions.isNotEmpty() && predictions[0].score > 1.0) binding.textView4.text = "${binding.textView4.text}${predictions[0].name}"
      }
      binding.gOverlay.addOnGestureListener(object: GestureOverlayView.OnGestureListener{
         var sv = (requireContext() as FragmentActivity).findViewById<ScrollView>(R.id.activity_sv)
         override fun onGestureStarted(overlay: GestureOverlayView?, event: MotionEvent?) {
            sv.requestDisallowInterceptTouchEvent(true)
         }

         override fun onGesture(overlay: GestureOverlayView?, event: MotionEvent?) {
         }

         override fun onGestureEnded(overlay: GestureOverlayView?, event: MotionEvent?) {
            sv.requestDisallowInterceptTouchEvent(false)
         }

         override fun onGestureCancelled(overlay: GestureOverlayView?, event: MotionEvent?) {
            sv.requestDisallowInterceptTouchEvent(false)
         }
      })
      binding.textView4.setOnClickListener {
         (it as TextView).text=""
      }
   }
}
class Tab1Fragment : Fragment() {
   private var _binding:FragmentTab1Binding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTab1Binding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}
class Tab2Fragment : Fragment() {
   private var _binding: FragmentTab2Binding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTab2Binding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}
class Tab3Fragment : Fragment() {
   private var _binding: FragmentTab3Binding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTab3Binding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}
class Tab4Fragment : Fragment() {
   private var _binding: FragmentTab4Binding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentTab4Binding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}
class TabLayoutFragment : Fragment() {
   private var _binding: FragmentTabLayoutBinding?=null
   private val binding get() = _binding!!
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = FragmentTabLayoutBinding.inflate(inflater,container,false)
      repeat(6){
         binding.tabLayout.addTab(binding.tabLayout.newTab())
      }
      val adapter = TabPagerAdapter(requireActivity(), binding.tabLayout.tabCount)
      binding.viewPager.adapter= adapter
      TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
         tab.text = "Tab ${(position + 1)} fragment"
      }.attach()
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}
class CardDemoFragment : Fragment() {
   private var _binding:FragmentCardDemoBinding?=null
   private val binding get() = _binding!!
   @SuppressLint("DiscouragedApi")
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
      binding.androidBugRv.layoutManager = LinearLayoutManager(requireContext(),
         LinearLayoutManager.VERTICAL,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
}
class CoroutineDemoFragment : Fragment() {
   private var _binding: FragmentCoroutineDemoBinding? = null
   private val binding get() = _binding!!
   private val coroutineScope = CoroutineScope(Dispatchers.Main)

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding=FragmentCoroutineDemoBinding.inflate(inflater,container,false)
      binding.seekBar2.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
         override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            binding.countTv.text = String.format("$progress coroutines")
         }
         override fun onStartTrackingTouch(seekBar: SeekBar?) {
         }
         override fun onStopTrackingTouch(seekBar: SeekBar?) {
         }

      })
      return binding.root
   }
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.button2.setOnClickListener {
         (1..binding.seekBar2.progress).forEach {
            val message = "Started Coroutine $it"
            binding.statusTv.text = message
            coroutineScope.launch(Dispatchers.Main) {
               Log.d(com.learning.ad.ff.observer.TAG, "$it on thread start")
               binding.statusTv.text = performTaskAsync(it).await()
            }
         }
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }
   private fun performTaskAsync(taskNumber: Int) =
      coroutineScope.async(Dispatchers.Main) {
         delay(5_000)
         return@async "Finished Coroutine $taskNumber"
      }
}
@Suppress("unused")
class FlowDemoFragment : Fragment() {
   private var _binding:FragmentFlowDemoBinding? = null
   private val binding get() = _binding!!
   private val _stateFlow = MutableStateFlow(0)
   private val stateFlow = _stateFlow.asStateFlow()
   private val _sharedFlow = MutableSharedFlow<Int>(replay = 10, onBufferOverflow = BufferOverflow.DROP_OLDEST)
   private val sharedFlow = _sharedFlow.asSharedFlow()
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentFlowDemoBinding.inflate(layoutInflater,container,false)
      // Inflate the layout for this fragment
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      lifecycleScope.launch {
         stateFlow.collectLatest {
            println("Counter = $it")
         }
      }
      lifecycleScope.launch {
         sharedFlow.collect {
            println("$it")
         }
      }
      binding.startBtn.setOnClickListener {
         handleFlow2()}
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
   private fun myFlow(): Flow<Int> = flow {
      var counter = 1
      while (counter < 6) {
         emit(counter)
         counter++
         delay(2000)
      }
   }
   private fun handleFlow() {
      lifecycleScope.launch {
         try {
            val elapsedTime = measureTimeMillis {
               val flow1 = (1..5).asFlow().onEach { delay(1000) }
               val flow2 = flowOf("one", "two", "three", "four").onEach { delay(1500) }
               flow1.combine(flow2) { value, string -> "$value, $string" }.collect { println(it) }
            }
            println("Duration = $elapsedTime")
         }
         finally { println("Flow stream ended.") }
      }
   }
   private fun doubleIt(value: Int) = flow {
      emit(value)
      delay(1000)
      emit(value + value)
   }
   private fun handleFlow1() {
      _stateFlow.value += 1
   }

   private fun handleFlow2() {
      var counter = 1
      lifecycleScope.launch {
         while (counter < 6) {
            _sharedFlow.emit(counter)
            counter++
            delay(2000)
         }
      }
   }
}
class SharedFlowDemoFragment : Fragment() {
   private var _binding: FragmentSharedFlowDemoBinding? = null
   private val binding get() = _binding!!
   private lateinit var viewModel:SharedFlowViewModel
   private val itemList = ArrayList<String>()
   private lateinit var adapter: RVadapter
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[SharedFlowViewModel::class.java]
   }

   private fun collectFlow() {
      adapter = RVadapter(itemList)
      binding.rv.layoutManager = LinearLayoutManager(requireContext())
      binding.rv.adapter = adapter
      lifecycleScope.launch {
         repeatOnLifecycle(Lifecycle.State.STARTED){
            viewModel.sharedFlow.collect { value ->
               Log.d(com.learning.ad.ff.observer.TAG,"Collecting $value")
               itemList.add(value.toString())
               adapter.notifyItemInserted(itemList.lastIndex)
               binding.rv.smoothScrollToPosition(adapter.itemCount)
            }
         }
      }
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSharedFlowDemoBinding.inflate(layoutInflater,container,false)
      collectFlow()
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}
class SqlDemoFragment : Fragment() {
   private var _binding:FragmentSqlDemoBinding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSqlDemoBinding.inflate(layoutInflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.addBtn.setOnClickListener {
         val values = ContentValues().apply {
            put(COLUMN_CUSTOMER_NAME,binding.nameEdt.text.toString())
            put(COLUMN_CUSTOMER_PHONE, binding.phoneEdt.text.toString())
         }
         val uri = requireContext().contentResolver.insert(MyContentProvider.CONTENT_URI, values)
         Log.d(com.learning.ad.ff.observer.TAG, uri.toString())
         binding.nameEdt.text.clear()
         binding.phoneEdt.text.clear()
      }
      binding.findBtn.setOnClickListener {
         //request a specific record
         val id= 2L
         val uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id)
         requireContext().contentResolver.query(
            uri, arrayOf(COLUMN_ID, COLUMN_CUSTOMER_NAME),
            "$COLUMN_ID = ?",
            arrayOf("$id"),
            null
         )?.use {
            if (it.moveToFirst()) {
               val id1 = it.getLong(it.getColumnIndexOrThrow(COLUMN_ID))
               val name = it.getString(it.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME))
               Log.d("SpecificRecord", "ID: $id1, Name: $name")
            } else {
               Log.d("SpecificRecord", "No record found")
            }
         }
      }
      binding.fetchAllBtn.setOnClickListener {
         requireContext().contentResolver.query(
            MyContentProvider.CONTENT_URI, arrayOf(COLUMN_ID, COLUMN_CUSTOMER_NAME),
            null,
            null,
            null
         )?.use {
            if (it.moveToFirst()) {
               do {
                  val id = it.getLong(it.getColumnIndexOrThrow(COLUMN_ID))
                  val name = it.getString(it.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME))
                  Log.d("Records", "ID: $id, Name: $name")
               } while (it.moveToNext())
            } else {
               Log.d("SpecificRecord", "No record found")
            }
         }
      }
      binding.deleteBtn.setOnClickListener {
         val id=2L
         val uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI,id)
         Log.d(com.learning.ad.ff.observer.TAG, requireContext().contentResolver.delete(uri,"$COLUMN_ID = ?", arrayOf("$id")).toString())
      }
      binding.updateBtn.setOnClickListener {
         val id = 2L
         val values = ContentValues().apply {
            put(COLUMN_CUSTOMER_NAME,binding.nameEdt.text.toString())
            put(COLUMN_CUSTOMER_PHONE, binding.phoneEdt.text.toString())
         }
         requireContext().contentResolver.update(ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id), values, "$COLUMN_ID=?", arrayOf("$id"))
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

}