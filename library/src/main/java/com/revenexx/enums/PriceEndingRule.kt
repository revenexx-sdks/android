package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceEndingRule(val value: String) {
    @SerializedName("exact")
    EXACT("exact"),
    @SerializedName("whole")
    WHOLE("whole"),
    @SerializedName("ending_99")
    ENDING_99("ending_99"),
    @SerializedName("ending_95")
    ENDING_95("ending_95"),
    @SerializedName("ending_50")
    ENDING_50("ending_50");

    override fun toString() = value
}