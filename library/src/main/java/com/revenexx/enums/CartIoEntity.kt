package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartIoEntity(val value: String) {
    @SerializedName("carts")
    CARTS("carts"),
    @SerializedName("cart_items")
    CART_ITEMS("cart_items");

    override fun toString() = value
}