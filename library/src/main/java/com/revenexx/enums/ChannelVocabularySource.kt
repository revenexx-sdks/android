package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelVocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema"),
    @SerializedName("table")
    TABLE("table");

    override fun toString() = value
}