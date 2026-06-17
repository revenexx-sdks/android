package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Range(val value: String) {
    @SerializedName("24h")
    24H("24h"),
    @SerializedName("30d")
    30D("30d"),
    @SerializedName("90d")
    90D("90d");

    override fun toString() = value
}