package com.udacity.shoestore.screens.detail

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.models.Shoe

class DetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentDetailBinding.inflate(inflater, container, false)

        with(binding) {
            shoe = Shoe()
            viewModel = this@DetailFragment.viewModel
            btnCancel.setOnClickListener {
                goToListing()
            }
        }

        viewModel.eventSaveLiveData.observe(viewLifecycleOwner) { hasSaved ->
            if (hasSaved) {
                viewModel.onItemSavedSuccess()
                goToListing()
            }
        }

        return binding.root
    }

    private fun goToListing() {
        findNavController().navigateUp()
    }
}

/*
    Docs: https://developer.android.com/topic/libraries/data-binding/two-way
    Credits: https://medium.com/@ssiasoft/two-way-data-binding-and-managing-text-inputs-in-android-9cc4701f628e
 */
@BindingAdapter("android:text")
fun EditText.bindAnyToString(value: Any?) {
    value?.let {
        setText(value.toString())
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun EditText.bindStringToDouble(): Double? {
    return text.toDouble()
}

private fun Editable.toDouble(): Double? {
    return if (this.toString().isNotEmpty()) this.toString().toDouble() else null
}
