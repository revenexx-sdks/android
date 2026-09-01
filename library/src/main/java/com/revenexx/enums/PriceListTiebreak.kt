package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceListTiebreak(val value: String) {
    @SerializedName("lowest_price")
    LOWEST_PRICE("lowest_price"),
    @SerializedName("highest_price")
    HIGHEST_PRICE("highest_price"),
    @SerializedName("newest")
    NEWEST("newest"),
    @SerializedName("code")
    CODE("code");

    override fun toString() = value
}