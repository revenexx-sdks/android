package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class SegmentMemberSource(val value: String) {
    @SerializedName("manual")
    MANUAL("manual"),
    @SerializedName("rule")
    RULE("rule");

    override fun toString() = value
}