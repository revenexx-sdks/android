package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderPaymentStatus(val value: String) {
    @SerializedName("open")
    OPEN("open"),
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("authorized")
    AUTHORIZED("authorized"),
    @SerializedName("paid")
    PAID("paid"),
    @SerializedName("partially_paid")
    PARTIALLY_PAID("partially_paid"),
    @SerializedName("refunded")
    REFUNDED("refunded"),
    @SerializedName("failed")
    FAILED("failed");

    override fun toString() = value
}