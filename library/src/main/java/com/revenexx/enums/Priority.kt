package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Priority(val value: String) {
    @SerializedName("normal")
    NORMAL("normal"),
    @SerializedName("high")
    HIGH("high");

    override fun toString() = value
}