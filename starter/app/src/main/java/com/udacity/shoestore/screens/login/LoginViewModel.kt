package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    fun doLogin() {
        _eventLogin.value = true
    }

    fun registerNewAccount() {
        _eventLogin.value = true
    }

    fun onLoggedIn() {
        _eventLogin.value = false
    }
}