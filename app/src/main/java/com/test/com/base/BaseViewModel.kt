package com.test.com.base

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.test.com.util.NetworkResult
import retrofit2.Response

abstract class BaseViewModel(application:Application):AndroidViewModel(application) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    fun <T> handleResponse(response: Response<T>): NetworkResult<T> {
        return when {
            response.message().contains("timeout") -> {
                NetworkResult.Error("TimeOut")
            }
            response.code() == 401 -> {
                NetworkResult.Error("UnAuthorized request")
            }
            response.isSuccessful -> {
                val boshSahifaData = response.body()
                NetworkResult.Success(boshSahifaData!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}