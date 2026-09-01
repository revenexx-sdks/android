package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class SegmentRulePreviewResponseRuleMatch(val value: String) {
    @SerializedName("all")
    ALL("all"),
    @SerializedName("any")
    ANY("any");

    override fun toString() = value
}