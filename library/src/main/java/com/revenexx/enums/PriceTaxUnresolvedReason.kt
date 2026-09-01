package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceTaxUnresolvedReason(val value: String) {
    @SerializedName("market_required")
    MARKET_REQUIRED("market_required"),
    @SerializedName("no_markets")
    NO_MARKETS("no_markets"),
    @SerializedName("no_tax_classes")
    NO_TAX_CLASSES("no_tax_classes"),
    @SerializedName("lookup_failed")
    LOOKUP_FAILED("lookup_failed");

    override fun toString() = value
}