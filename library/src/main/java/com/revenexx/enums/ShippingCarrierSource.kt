package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingCarrierSource(val value: String) {
    @SerializedName("method")
    METHOD("method"),
    @SerializedName("method_code")
    METHOD_CODE("method_code"),
    @SerializedName("method_text")
    METHOD_TEXT("method_text"),
    @SerializedName("tenant_default")
    TENANT_DEFAULT("tenant_default"),
    @SerializedName("tenant_default_text")
    TENANT_DEFAULT_TEXT("tenant_default_text");

    override fun toString() = value
}