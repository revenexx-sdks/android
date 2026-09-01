package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderFulfillmentStatus(val value: String) {
    @SerializedName("unfulfilled")
    UNFULFILLED("unfulfilled"),
    @SerializedName("partial")
    PARTIAL("partial"),
    @SerializedName("fulfilled")
    FULFILLED("fulfilled");

    override fun toString() = value
}