package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingTaxSource(val value: String) {
    @SerializedName("method")
    METHOD("method"),
    @SerializedName("tenant_class")
    TENANT_CLASS("tenant_class"),
    @SerializedName("market_default")
    MARKET_DEFAULT("market_default"),
    @SerializedName("tenant_default")
    TENANT_DEFAULT("tenant_default");

    override fun toString() = value
}