package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PaymentMethodKind(val value: String) {
    @SerializedName("self_managed")
    SELF_MANAGED("self_managed"),
    @SerializedName("psp")
    PSP("psp");

    override fun toString() = value
}