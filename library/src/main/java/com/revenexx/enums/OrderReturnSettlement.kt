package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderReturnSettlement(val value: String) {
    @SerializedName("refund")
    REFUND("refund"),
    @SerializedName("partial_refund")
    PARTIAL_REFUND("partial_refund"),
    @SerializedName("replacement")
    REPLACEMENT("replacement"),
    @SerializedName("repair")
    REPAIR("repair"),
    @SerializedName("store_credit")
    STORE_CREDIT("store_credit");

    override fun toString() = value
}