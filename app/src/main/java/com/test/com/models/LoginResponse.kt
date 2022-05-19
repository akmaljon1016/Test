package com.test.com.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("fullName")
    var fullName: String,
    @SerializedName("login")
    var login: String,
    @SerializedName("token")
    var token: String
)