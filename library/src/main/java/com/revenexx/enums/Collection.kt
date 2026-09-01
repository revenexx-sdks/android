package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Collection(val value: String) {
    @SerializedName("products")
    PRODUCTS("products");

    override fun toString() = value
}