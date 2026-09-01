package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ApplyMode(val value: String) {
    @SerializedName("upsert")
    UPSERT("upsert"),
    @SerializedName("full-sync")
    FULL_SYNC("full-sync"),
    @SerializedName("append")
    APPEND("append");

    override fun toString() = value
}