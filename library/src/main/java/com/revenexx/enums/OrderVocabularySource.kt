package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class OrderVocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema"),
    @SerializedName("app")
    APP("app");

    override fun toString() = value
}