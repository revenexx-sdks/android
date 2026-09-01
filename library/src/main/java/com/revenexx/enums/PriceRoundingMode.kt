package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceRoundingMode(val value: String) {
    @SerializedName("half_up")
    HALF_UP("half_up"),
    @SerializedName("half_even")
    HALF_EVEN("half_even"),
    @SerializedName("up")
    UP("up"),
    @SerializedName("down")
    DOWN("down");

    override fun toString() = value
}