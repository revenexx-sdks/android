package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceEntriesBulkMode(val value: String) {
    @SerializedName("upsert")
    UPSERT("upsert"),
    @SerializedName("append")
    APPEND("append");

    override fun toString() = value
}