package com.test.com.fragments.detailedview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.test.com.R
import com.test.com.databinding.FragmentDetailedViewBinding

class DetailedViewFragment : Fragment(R.layout.fragment_detailed_view) {
    val binding: FragmentDetailedViewBinding by viewBinding(FragmentDetailedViewBinding::bind)
    val detailedViewViewModel by viewModels<DetailedViewViewModel>()
    private val args by navArgs<DetailedViewFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(args.product.photoUrl).into(binding.imgProduct)
        binding.txtProductId.setText(requireContext().getString(R.string.id) + "\n" + args.product.id)
        binding.txtProductName.setText(requireContext().getString(R.string.name) + "\n" + args.product.name)
        binding.txtDescription.setText(requireContext().getString(R.string.description) + "\n" + args.product.description)
        binding.txtProductPrice.setText(requireContext().getString(R.string.price) + "\n" + args.product.price)
    }
}