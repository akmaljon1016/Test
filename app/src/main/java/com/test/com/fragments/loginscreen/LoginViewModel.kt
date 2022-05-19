package com.test.com.fragments.loginscreen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.com.base.BaseViewModel
import com.test.com.models.Login
import com.test.com.models.LoginResponse
import com.test.com.repository.Repository
import com.test.com.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository:Repository,
    application: Application) : BaseViewModel(application) {

    var _login: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()
    val login: LiveData<NetworkResult<LoginResponse>> get() = _login

    suspend fun login(login:Login) =viewModelScope.launch {
        loginSafeCall(login)
    }

    private suspend fun loginSafeCall(login: Login) {
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.login(login)
                _login.value = handleResponse(response)
            } catch (e: Exception) {
                _login.value = NetworkResult.Error(e.message)
            }
        } else {
            _login.value = NetworkResult.Error("No Internet Connection")
        }
    }
}