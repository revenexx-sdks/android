package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class VocabularySource(val value: String) {
    @SerializedName("schema")
    SCHEMA("schema"),
    @SerializedName("table")
    TABLE("table"),
    @SerializedName("tenant")
    TENANT("tenant"),
    @SerializedName("defaults")
    DEFAULTS("defaults");

    override fun toString() = value
}