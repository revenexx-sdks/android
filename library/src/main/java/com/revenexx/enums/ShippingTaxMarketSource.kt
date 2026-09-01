package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingTaxMarketSource(val value: String) {
    @SerializedName("request")
    REQUEST("request"),
    @SerializedName("header")
    HEADER("header"),
    @SerializedName("country")
    COUNTRY("country"),
    @SerializedName("sole_market")
    SOLE_MARKET("sole_market");

    override fun toString() = value
}