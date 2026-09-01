package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PricingType(val value: String) {
    @SerializedName("fixed")
    FIXED("fixed"),
    @SerializedName("free")
    FREE("free"),
    @SerializedName("matrix")
    MATRIX("matrix");

    override fun toString() = value
}