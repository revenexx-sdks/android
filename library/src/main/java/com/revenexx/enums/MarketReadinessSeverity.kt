package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketReadinessSeverity(val value: String) {
    @SerializedName("blocking")
    BLOCKING("blocking"),
    @SerializedName("warning")
    WARNING("warning"),
    @SerializedName("info")
    INFO("info");

    override fun toString() = value
}