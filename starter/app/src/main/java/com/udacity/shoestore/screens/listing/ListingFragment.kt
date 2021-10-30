package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentListingBinding


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

        setHasOptionsMenu(true)

        viewModel.shoeLiveData.observe(viewLifecycleOwner) { shoes ->
            shoes.forEach { shoe ->
                with(binding.ctnShoe) {
                    addView(createTextView(shoe.name, R.style.AppTheme_Headline))
                    addView(createTextView(shoe.company, R.style.AppTheme_Overline))
                    addView(createTextView(shoe.size.toString(), R.style.AppTheme_Subtitle))
                    addView(createTextView(shoe.description, R.style.AppTheme_Caption))
                    addView(createViewSeparator())
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }

        return binding.root
    }

    private fun createTextView(text: String, style: Int): TextView {
        return TextView(context, null, 0, style).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 8.toDp()
            }
        }.also {
            it.text = text
        }
    }

    private fun createViewSeparator(): View {
        return View(context, null, 0, R.style.AppTheme_Separator).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1).also {
                it.setMargins(8.toDp(), 16.toDp(), 8.toDp(), 0)
            }
            setBackgroundColor(ContextCompat.getColor(context, R.color.darker_gray))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun Int.toDp(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}
