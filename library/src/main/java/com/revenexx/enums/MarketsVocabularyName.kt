package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketsVocabularyName(val value: String) {
    @SerializedName("market-statuses")
    MARKET_STATUSES("market-statuses");

    override fun toString() = value
}