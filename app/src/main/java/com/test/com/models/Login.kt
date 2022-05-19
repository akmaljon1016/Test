package com.test.com.models


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("login")
    var login: String,
    @SerializedName("password")
    var password: String
)