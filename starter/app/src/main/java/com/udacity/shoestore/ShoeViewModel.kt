package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val shoes: MutableList<Shoe> = mutableListOf()

    private val _shoeLiveData = MutableLiveData<List<Shoe>>()
    val shoeLiveData: LiveData<List<Shoe>>
        get() = _shoeLiveData

    private val _eventSaveLiveData = MutableLiveData<Boolean>()
    val eventSaveLiveData: LiveData<Boolean>
        get() = _eventSaveLiveData

    fun addShoe(shoe: Shoe) {
        with (shoe) {
            if (name.isEmpty() || size == null || company.isEmpty() || description.isEmpty()) return
        }

        shoes.add(shoe)
        _shoeLiveData.value = shoes
        _eventSaveLiveData.value = true
        Timber.i("Added new shoe")
    }

    fun onItemSavedSuccess() {
        _eventSaveLiveData.value = false
    }
}