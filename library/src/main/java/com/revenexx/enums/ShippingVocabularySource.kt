package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ShippingVocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema"),
    @SerializedName("table")
    TABLE("table");

    override fun toString() = value
}