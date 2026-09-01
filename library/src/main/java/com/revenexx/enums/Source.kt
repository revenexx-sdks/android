package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Source(val value: String) {
    @SerializedName("manual")
    MANUAL("manual"),
    @SerializedName("rule")
    RULE("rule");

    override fun toString() = value
}