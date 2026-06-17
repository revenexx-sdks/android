package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingMethodMatrixBasis(val value: String) {
    @SerializedName("weight")
    WEIGHT("weight"),
    @SerializedName("quantity")
    QUANTITY("quantity"),
    @SerializedName("order_value")
    ORDER_VALUE("order_value"),
    @SerializedName("attribute")
    ATTRIBUTE("attribute");

    override fun toString() = value
}