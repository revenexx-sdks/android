package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartItemType(val value: String) {
    @SerializedName("product")
    PRODUCT("product"),
    @SerializedName("configuration")
    CONFIGURATION("configuration"),
    @SerializedName("custom")
    CUSTOM("custom");

    override fun toString() = value
}