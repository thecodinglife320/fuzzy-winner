package com.learning.ad.ff.repository

import android.app.Application
import com.learning.ad.ff.db.AppDatabase
import com.learning.ad.ff.model.Product

class ProductRepository(application: Application) {

    private var productDao= AppDatabase.getDatabase(application).productDao()
    var allProducts = productDao.getAllProducts()
    var searchResults = productDao.searchByName("")

    suspend fun insert(product: Product) = productDao.insert(product)
    suspend fun delete(product: Product) = productDao.delete(product)
    suspend fun update(product: Product) = productDao.update(product)
}