package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartVocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema");

    override fun toString() = value
}