package com.learning.ad.ff.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learning.ad.ff.R
import com.learning.ad.ff.model.Product
import com.learning.ad.ff.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) :AndroidViewModel(application) {
    private val repository:ProductRepository = ProductRepository(application)
    var idProduct = MutableLiveData("")
    var nameProduct = MutableLiveData("")
    var quantityProduct = MutableLiveData("")
    var allProducts = repository.allProducts
    private var selectedProduct: Product? = null
    val alertProductName = getApplication<Application>().getString(R.string.name_notify)
    val alertProductQuantity = getApplication<Application>().getString(R.string.quantity_notify)
    var isProductSelected = MutableLiveData(true)
    fun insert() {
        if (nameProduct.value.isNullOrEmpty() || quantityProduct.value.isNullOrEmpty()) return
        val product = Product(name = nameProduct.value!!, quantity = quantityProduct.value!!.toInt())
        viewModelScope.launch {
            repository.insert(product)
        }
        resetValue()
    }
    fun delete(){
        if (selectedProduct==null){
            isProductSelected.value = false
        }else{
            viewModelScope.launch {
                repository.delete(selectedProduct!!)
            }
            resetValue()
        }
    }
    fun onSelectProduct(product: Product){
        selectedProduct = product
        nameProduct.value = product.name
        quantityProduct.value = "${product.quantity}"
        idProduct.value = "${product.id}"
    }
    private fun resetValue(){
        nameProduct.value = ""
        quantityProduct.value = ""
        idProduct.value = ""
        selectedProduct = null
    }
    fun update(){
        if (selectedProduct==null){
            isProductSelected.value = false
        } else{
            if (nameProduct.value.isNullOrEmpty() || quantityProduct.value.isNullOrEmpty()) return
            else{
                selectedProduct!!.name = nameProduct.value!!.toString()
                selectedProduct!!.quantity = quantityProduct.value!!.toInt()
                viewModelScope.launch {
                    repository.update(selectedProduct!!)
                }
                resetValue()
            }
        }
    }
}