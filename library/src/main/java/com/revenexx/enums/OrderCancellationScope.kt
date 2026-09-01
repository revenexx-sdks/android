package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderCancellationScope(val value: String) {
    @SerializedName("order")
    ORDER("order"),
    @SerializedName("items")
    ITEMS("items");

    override fun toString() = value
}