package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketPricingSource(val value: String) {
    @SerializedName("market")
    MARKET("market"),
    @SerializedName("tenant")
    TENANT("tenant"),
    @SerializedName("unset")
    UNSET("unset");

    override fun toString() = value
}