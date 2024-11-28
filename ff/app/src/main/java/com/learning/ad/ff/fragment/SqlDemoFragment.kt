package com.learning.ad.ff.fragment

import android.content.*
import android.os.*
import android.util.*
import android.view.*
import androidx.fragment.app.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.db.CryCafeDB.Companion.COLUMN_CUSTOMER_NAME
import com.learning.ad.ff.db.CryCafeDB.Companion.COLUMN_CUSTOMER_PHONE
import com.learning.ad.ff.db.CryCafeDB.Companion.COLUMN_ID
import com.learning.ad.ff.observer.*
import com.learning.ad.ff.provider.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SqlDemoFragment : Fragment() {
   private var param1: String? = null
   private var param2: String? = null
   private var _binding:FragmentSqlDemoBinding?=null

   private val binding get() = _binding!!
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      arguments?.let {
         param1 = it.getString(ARG_PARAM1)
         param2 = it.getString(ARG_PARAM2)
      }
   }
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
         Log.d(TAG, uri.toString())
         binding.nameEdt.text.clear()
         binding.phoneEdt.text.clear()
      }
      binding.findBtn.setOnClickListener {
         //request a specific record
         val id= 2L
         val uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id)
         requireContext().contentResolver.query(
            uri, arrayOf(COLUMN_ID,COLUMN_CUSTOMER_NAME),
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
            MyContentProvider.CONTENT_URI, arrayOf(COLUMN_ID,COLUMN_CUSTOMER_NAME),
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
         Log.d(TAG, requireContext().contentResolver.delete(uri,"$COLUMN_ID = ?", arrayOf("$id")).toString())
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