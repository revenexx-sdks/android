package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PagesVocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema");

    override fun toString() = value
}