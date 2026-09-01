package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketDefaultLocaleSource(val value: String) {
    @SerializedName("market")
    MARKET("market"),
    @SerializedName("market_first")
    MARKET_FIRST("market_first"),
    @SerializedName("tenant_fallback")
    TENANT_FALLBACK("tenant_fallback");

    override fun toString() = value
}