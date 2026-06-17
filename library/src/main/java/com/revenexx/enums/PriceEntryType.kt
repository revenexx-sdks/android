package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceEntryType(val value: String) {
    @SerializedName("standard")
    STANDARD("standard"),
    @SerializedName("on_request")
    ON_REQUEST("on_request");

    override fun toString() = value
}