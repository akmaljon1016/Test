package com.test.com.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItem(
    @SerializedName("description")
    var description: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("photoUrl")
    var photoUrl: String,
    @SerializedName("price")
    var price: Double
):Parcelable