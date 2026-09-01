package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class SegmentRulePreviewResponseTarget(val value: String) {
    @SerializedName("organizations")
    ORGANIZATIONS("organizations");

    override fun toString() = value
}