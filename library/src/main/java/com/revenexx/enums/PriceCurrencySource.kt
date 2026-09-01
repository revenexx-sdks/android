package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceCurrencySource(val value: String) {
    @SerializedName("request")
    REQUEST("request"),
    @SerializedName("market")
    MARKET("market"),
    @SerializedName("tenant")
    TENANT("tenant"),
    @SerializedName("fallback")
    FALLBACK("fallback");

    override fun toString() = value
}