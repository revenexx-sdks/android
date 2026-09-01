package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketReadinessWarnings(val value: String) {
    @SerializedName("locales")
    LOCALES("locales"),
    @SerializedName("currencies")
    CURRENCIES("currencies"),
    @SerializedName("tax_classes")
    TAX_CLASSES("tax_classes"),
    @SerializedName("tax_basis")
    TAX_BASIS("tax_basis");

    override fun toString() = value
}