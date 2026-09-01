package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ProductsKind(val value: String) {
    @SerializedName("simple")
    SIMPLE("simple"),
    @SerializedName("model")
    MODEL("model"),
    @SerializedName("variant")
    VARIANT("variant");

    override fun toString() = value
}