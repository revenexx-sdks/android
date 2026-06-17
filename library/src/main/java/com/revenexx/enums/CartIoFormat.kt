package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartIoFormat(val value: String) {
    @SerializedName("json")
    JSON("json"),
    @SerializedName("csv")
    CSV("csv");

    override fun toString() = value
}