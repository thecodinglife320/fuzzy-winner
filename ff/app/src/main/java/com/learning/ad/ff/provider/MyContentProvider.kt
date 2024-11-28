package com.learning.ad.ff.provider

import android.content.*
import android.database.*
import android.net.*
import android.util.*
import com.learning.ad.ff.db.*
import com.learning.ad.ff.observer.*

class MyContentProvider : ContentProvider() {
   private lateinit var db: CryCafeDB
   private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)
   companion object {
      private const val CUSTOMERS =1
      private const val CUSTOMER_ID = 2
      private const val AUTHORITY = "com.learning.ad.ff.provider.MyContentProvider"
      val CONTENT_URI : Uri = Uri.parse("content://$AUTHORITY/${CryCafeDB.TABLE_CUSTOMERS}")
   }
   init {
      sURIMatcher.addURI(AUTHORITY, "customers", CUSTOMERS)
      sURIMatcher.addURI(AUTHORITY, "customers/#", CUSTOMER_ID)
   }
   override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int =
      when(sURIMatcher.match(uri)){
         CUSTOMER_ID-> db.writableDatabase.delete(CryCafeDB.TABLE_CUSTOMERS, selection, selectionArgs)
         else -> throw IllegalArgumentException("Unknown URI")
      }

   override fun getType(uri: Uri): String? {
      TODO(
         "Implement this to handle requests for the MIME type of the data" +
           "at the given URI"
      )
   }

   override fun insert(uri: Uri, values: ContentValues?): Uri? {
      val id: Long
      when (sURIMatcher.match(uri)){
         CUSTOMERS->id = db.writableDatabase.insert(CryCafeDB.TABLE_CUSTOMERS, null, values)
         else ->throw IllegalArgumentException("Unknown URI: $uri")
      }
      return Uri.parse("${CryCafeDB.TABLE_CUSTOMERS}/$id")
   }

   override fun onCreate(): Boolean {
      db = CryCafeDB(context!!)
      Log.d(TAG,db.toString())
      return true
   }

   override fun query(
      uri: Uri, projection: Array<String>?, selection: String?,
      selectionArgs: Array<String>?, sortOrder: String?
   ): Cursor? {
      return when(sURIMatcher.match(uri)){
         CUSTOMER_ID, CUSTOMERS->{
            db.readableDatabase.query(CryCafeDB.TABLE_CUSTOMERS,projection,selection,selectionArgs,null,null,sortOrder)
         }
         else->throw IllegalArgumentException("Unknown URI")
      }
   }

   override fun update(
      uri: Uri, values: ContentValues?, selection: String?,
      selectionArgs: Array<String>?
   ): Int = when(sURIMatcher.match(uri)){
      CUSTOMER_ID-> db.writableDatabase.update(CryCafeDB.TABLE_CUSTOMERS, values,selection,selectionArgs)
      else -> throw IllegalArgumentException("Unknown URI: $uri")
   }
}