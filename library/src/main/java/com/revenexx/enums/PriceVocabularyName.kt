package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceVocabularyName(val value: String) {
    @SerializedName("list-statuses")
    LIST_STATUSES("list-statuses"),
    @SerializedName("price-types")
    PRICE_TYPES("price-types"),
    @SerializedName("tax-bases")
    TAX_BASES("tax-bases");

    override fun toString() = value
}