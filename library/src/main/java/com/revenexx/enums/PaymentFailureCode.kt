package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PaymentFailureCode(val value: String) {
    @SerializedName("provider_unavailable")
    PROVIDER_UNAVAILABLE("provider_unavailable"),
    @SerializedName("provider_unreachable")
    PROVIDER_UNREACHABLE("provider_unreachable"),
    @SerializedName("provider_not_configured")
    PROVIDER_NOT_CONFIGURED("provider_not_configured"),
    @SerializedName("provider_declined")
    PROVIDER_DECLINED("provider_declined"),
    @SerializedName("provider_error")
    PROVIDER_ERROR("provider_error");

    override fun toString() = value
}