package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingVocabulariesGetName(val value: String) {
    @SerializedName("carrier-statuses")
    CARRIER_STATUSES("carrier-statuses"),
    @SerializedName("matrix-bases")
    MATRIX_BASES("matrix-bases"),
    @SerializedName("pricing-types")
    PRICING_TYPES("pricing-types"),
    @SerializedName("service-levels")
    SERVICE_LEVELS("service-levels"),
    @SerializedName("weight-units")
    WEIGHT_UNITS("weight-units");

    override fun toString() = value
}