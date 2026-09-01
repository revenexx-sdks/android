package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PaymentStatus(val value: String) {
    @SerializedName("created")
    CREATED("created"),
    @SerializedName("requires_action")
    REQUIRES_ACTION("requires_action"),
    @SerializedName("authorized")
    AUTHORIZED("authorized"),
    @SerializedName("captured")
    CAPTURED("captured"),
    @SerializedName("failed")
    FAILED("failed"),
    @SerializedName("cancelled")
    CANCELLED("cancelled"),
    @SerializedName("refunded")
    REFUNDED("refunded");

    override fun toString() = value
}