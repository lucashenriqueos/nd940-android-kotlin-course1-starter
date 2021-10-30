package com.udacity.shoestore.screens.detail

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )


        binding.btnSave.setOnClickListener {
            viewModel.addNewShoe(
                name = binding.edtProductName.text.toString(),
                size = binding.edtProductSize.text.toDouble(),
                company = binding.edtProductCompany.text.toString(),
                description = binding.edtProductDescription.text.toString()
            )
        }

        binding.btnCancel.setOnClickListener {
            goToListing()
        }

        viewModel.eventSaveLiveData.observe(viewLifecycleOwner) { hasSaved ->
            if (hasSaved) {
                viewModel.onItemSavedSuccess()
                goToListing()
            }
        }

        binding.viewModel = viewModel
        return binding.root
    }

    private fun goToListing() {
        findNavController().navigateUp()
    }
}

private fun Editable.toDouble(): Double? {
    return if (this.toString().isNotEmpty()) this.toString().toDouble() else null
}
