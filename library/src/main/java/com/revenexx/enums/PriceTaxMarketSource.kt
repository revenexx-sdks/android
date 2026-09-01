package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceTaxMarketSource(val value: String) {
    @SerializedName("request")
    REQUEST("request"),
    @SerializedName("header")
    HEADER("header"),
    @SerializedName("sole_market")
    SOLE_MARKET("sole_market");

    override fun toString() = value
}