package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val _shoeLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoeLiveData: LiveData<MutableList<Shoe>>
        get() = _shoeLiveData

    private val _eventSaveLiveData = MutableLiveData<Boolean>()
    val eventSaveLiveData: LiveData<Boolean>
        get() = _eventSaveLiveData

    init {
        Timber.i("Initialized")
        _shoeLiveData.value = mutableListOf(
            Shoe(
                name = "Superstar",
                size = 42.0,
                company = "Adidas",
                description = "Adidas superstar"
            )
        )
    }

    fun addNewShoe(name: String, size: Double?, company: String, description: String) {
        if (name.isEmpty() || size == null || company.isEmpty() || description.isEmpty()) return

        Timber.i("Added new shoe")
        _shoeLiveData.value?.add(
            Shoe(
                name = name,
                size = size,
                company = company,
                description = description
            )
        )
        _eventSaveLiveData.value = true
    }

    fun onItemSavedSuccess() {
        _eventSaveLiveData.value = false
    }
}