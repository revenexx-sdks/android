package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingTaxContextVia(val value: String) {
    @SerializedName("tenant_default")
    TENANT_DEFAULT("tenant_default");

    override fun toString() = value
}