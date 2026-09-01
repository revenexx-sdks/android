package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderVocabularySummaryName(val value: String) {
    @SerializedName("cancellation-scopes")
    CANCELLATION_SCOPES("cancellation-scopes"),
    @SerializedName("comment-visibilities")
    COMMENT_VISIBILITIES("comment-visibilities"),
    @SerializedName("fulfillment-statuses")
    FULFILLMENT_STATUSES("fulfillment-statuses"),
    @SerializedName("item-types")
    ITEM_TYPES("item-types"),
    @SerializedName("payment-statuses")
    PAYMENT_STATUSES("payment-statuses"),
    @SerializedName("return-resolutions")
    RETURN_RESOLUTIONS("return-resolutions"),
    @SerializedName("return-statuses")
    RETURN_STATUSES("return-statuses"),
    @SerializedName("statuses")
    STATUSES("statuses");

    override fun toString() = value
}