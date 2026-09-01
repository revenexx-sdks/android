package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketLocaleGranularity(val value: String) {
    @SerializedName("regional")
    REGIONAL("regional"),
    @SerializedName("language")
    LANGUAGE("language");

    override fun toString() = value
}