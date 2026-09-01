package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingCarriersListStatus(val value: String) {
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("paused")
    PAUSED("paused"),
    @SerializedName("retired")
    RETIRED("retired");

    override fun toString() = value
}