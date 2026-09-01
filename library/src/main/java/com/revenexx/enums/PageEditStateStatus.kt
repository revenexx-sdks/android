package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PageEditStateStatus(val value: String) {
    @SerializedName("active")
    ACTIVE("active"),
    @SerializedName("scheduled")
    SCHEDULED("scheduled"),
    @SerializedName("archived")
    ARCHIVED("archived"),
    @SerializedName("published")
    PUBLISHED("published");

    override fun toString() = value
}