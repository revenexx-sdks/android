package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelPolicySource(val value: String) {
    @SerializedName("tenant")
    TENANT("tenant"),
    @SerializedName("channel")
    CHANNEL("channel");

    override fun toString() = value
}