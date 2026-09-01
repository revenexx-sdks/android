package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class InventoryVocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema");

    override fun toString() = value
}