package com.learning.ad.ff.db

import android.content.*
import android.database.sqlite.*

class CryCafeDB(
   context: Context,
) :SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){
   companion object{
      private const val DATABASE_VERSION =1
      private const val DATABASE_NAME = "CryCafe.db"
      const val TABLE_CUSTOMERS = "customers"
      const val COLUMN_ID = "_id"
      const val COLUMN_CUSTOMER_NAME = "name"
      const val COLUMN_CUSTOMER_PHONE = "phoneNumber"
   }
   override fun onCreate(db: SQLiteDatabase?) {
      val sql = "CREATE TABLE $TABLE_CUSTOMERS ($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_CUSTOMER_NAME TEXT,$COLUMN_CUSTOMER_PHONE TEXT)"
      db!!.execSQL(sql)
   }
   override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CUSTOMERS")
      onCreate(db)
   }
}