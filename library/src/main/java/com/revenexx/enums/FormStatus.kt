package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class FormStatus(val value: String) {
    @SerializedName("draft")
    DRAFT("draft"),
    @SerializedName("live")
    LIVE("live"),
    @SerializedName("archived")
    ARCHIVED("archived");

    override fun toString() = value
}