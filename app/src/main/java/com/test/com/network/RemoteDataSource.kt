package com.test.com.network

import android.util.Log
import android.util.Log.d
import com.test.com.models.Login
import com.test.com.models.LoginResponse
import com.test.com.models.ProductsItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun login(login: Login): Response<LoginResponse> {
        return networkApi.login(login)
    }

    suspend fun getProduct(): Response<List<ProductsItem>> {
        return networkApi.getProduct()
    }

    suspend fun getSingleProduct(): Response<ProductsItem> {
        return networkApi.getSingleProduct()
    }
}