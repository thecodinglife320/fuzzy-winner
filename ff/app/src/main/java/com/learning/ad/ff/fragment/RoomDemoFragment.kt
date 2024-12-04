package com.learning.ad.ff.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.learning.ad.ff.R
import com.learning.ad.ff.adapter.ProductAdapter
import com.learning.ad.ff.databinding.FragmentRoomDemoBinding
import com.learning.ad.ff.model.Product
import com.learning.ad.ff.viewmodel.ProductViewModel

class RoomDemoFragment : Fragment(), ProductAdapter.ProductClickListener {
    private var _binding:FragmentRoomDemoBinding?=null
    private val binding get() = _binding!!
    private lateinit var productViewModel: ProductViewModel
    private var adapter = ProductAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_demo,container,false)
        val factory = ProductViewModelFactory(requireActivity().application)
        productViewModel = ViewModelProvider(this,factory)[ProductViewModel::class.java]
        binding.viewmodel = productViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.productRv.adapter = adapter
        binding.productRv.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.allProducts.observe(viewLifecycleOwner){
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
        productViewModel.isProductSelected.observe(viewLifecycleOwner){
            if (!it) Snackbar.make(view, getString(R.string.select_product_notify), Snackbar.LENGTH_INDEFINITE).setAction("OK",NoOpClickListener()).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onProductClick(product: Product) {
        productViewModel.onSelectProduct(product)
    }
}

class NoOpClickListener : View.OnClickListener {
    override fun onClick(v: View?) {
        // Do nothing
    }
}