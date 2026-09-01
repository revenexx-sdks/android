package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelInactiveBehavior(val value: String) {
    @SerializedName("serve")
    SERVE("serve"),
    @SerializedName("block")
    BLOCK("block");

    override fun toString() = value
}