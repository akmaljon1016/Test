package com.test.com.fragments.product

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.com.base.BaseViewModel
import com.test.com.models.ProductsItem
import com.test.com.network.NetworkApi
import com.test.com.repository.Repository
import com.test.com.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    val repository: Repository
) : BaseViewModel(application) {

    var _product: MutableLiveData<NetworkResult<List<ProductsItem>>> = MutableLiveData()
    val product: LiveData<NetworkResult<List<ProductsItem>>> get() = _product


    suspend fun getProduct() = viewModelScope.launch {
        getProductSafeCall()
    }

    private suspend fun getProductSafeCall() {
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getProduct()
                _product.value = handleResponse(response)
            } catch (e: Exception) {
                _product.value = NetworkResult.Error(e.message)
            }
        } else {
            _product.value = NetworkResult.Error("No Internet Connection")
        }
    }
}