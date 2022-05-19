package com.test.com.fragments.product

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.com.R
import com.test.com.adapters.RecyclerviewAdapter
import com.test.com.base.BaseFragment
import com.test.com.databinding.FragmentLoginBinding
import com.test.com.databinding.FragmentProductBinding
import com.test.com.models.ProductsItem
import com.test.com.util.Constants
import com.test.com.util.NetworkResult
import com.test.com.util.Preferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ProductFragment : BaseFragment(R.layout.fragment_product) {

    private val binding: FragmentProductBinding by viewBinding(FragmentProductBinding::bind)
    val productViewModel by viewModels<ProductViewModel>()
    lateinit var recyclerviewAdapter: RecyclerviewAdapter
    @Inject
    lateinit var sharedPreferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Constants.TOKEN_FOR_NETWORKING=sharedPreferences.readBearerToken()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewAdapter = RecyclerviewAdapter(requireContext())
        lifecycleScope.launch {
            productViewModel.getProduct()
        }
        binding.recyclerview.adapter = recyclerviewAdapter
        productViewModel.product.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    alertDialog.setTitle("Xatolik")
                    alertDialog.setMessage(it.message.toString())
                    alertDialog.show()
                    binding.progress.visibility = View.GONE
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                    recyclerviewAdapter.submitList(it.data)
                }
            }
        }
        recyclerviewAdapter.setOnItemClickedListener(object :
            RecyclerviewAdapter.OnItemClickedListener {
            override fun onItemClicked(productsItem: ProductsItem) {
                val action=ProductFragmentDirections.actionProductFragmentToDetailedViewFragment(productsItem)
                findNavController().navigate(action)
            }
        })
    }
}