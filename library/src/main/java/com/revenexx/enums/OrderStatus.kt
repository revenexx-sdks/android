package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderStatus(val value: String) {
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("placed")
    PLACED("placed"),
    @SerializedName("in_fulfillment")
    IN_FULFILLMENT("in_fulfillment"),
    @SerializedName("completed")
    COMPLETED("completed"),
    @SerializedName("cancelled")
    CANCELLED("cancelled");

    override fun toString() = value
}