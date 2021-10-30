package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.databinding.LayoutShoeItemBinding

class ListingFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentListingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_listing,
            container,
            false
        )

        viewModel.shoeLiveData.observe(viewLifecycleOwner) { shoes ->
            shoes.forEach { shoe ->
                with(LayoutShoeItemBinding.inflate(layoutInflater)) {
                    itemName.text = shoe.name
                    itemCompany.text = shoe.company
                    itemDescription.text = shoe.description
                    itemSize.text = shoe.size.toString()

                    binding.ctnShoe.addView(ctnShoeItem)
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }

        return binding.root
    }
}