package com.test.com.network

import com.test.com.models.Login
import com.test.com.models.LoginResponse
import com.test.com.models.ProductsItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkApi {

    @POST("authenticate")
    suspend fun login(
        @Body login: Login
    ): Response<LoginResponse>

    @GET("products")
    suspend fun getProduct(): Response<List<ProductsItem>>

    @GET("products/{id}")
    suspend fun getSingleProduct():Response<ProductsItem>
}