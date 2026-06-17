package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PageStatus(val value: String) {
    @SerializedName("draft")
    DRAFT("draft"),
    @SerializedName("published")
    PUBLISHED("published"),
    @SerializedName("archived")
    ARCHIVED("archived");

    override fun toString() = value
}