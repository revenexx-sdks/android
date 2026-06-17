package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PaymentFeeType(val value: String) {
    @SerializedName("none")
    NONE("none"),
    @SerializedName("fixed")
    FIXED("fixed"),
    @SerializedName("percent")
    PERCENT("percent");

    override fun toString() = value
}