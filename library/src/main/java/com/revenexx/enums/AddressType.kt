package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class AddressType(val value: String) {
    @SerializedName("billing")
    BILLING("billing"),
    @SerializedName("shipping")
    SHIPPING("shipping");

    override fun toString() = value
}