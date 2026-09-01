package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketsListStatus(val value: String) {
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("inactive")
    INACTIVE("inactive");

    override fun toString() = value
}