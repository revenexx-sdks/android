package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceTaxBasis(val value: String) {
    @SerializedName("net")
    NET("net"),
    @SerializedName("gross")
    GROSS("gross");

    override fun toString() = value
}