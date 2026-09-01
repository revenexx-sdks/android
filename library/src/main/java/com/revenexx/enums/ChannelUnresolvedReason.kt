package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelUnresolvedReason(val value: String) {
    @SerializedName("channel_required")
    CHANNEL_REQUIRED("channel_required"),
    @SerializedName("no_default_channel")
    NO_DEFAULT_CHANNEL("no_default_channel"),
    @SerializedName("unknown_channel")
    UNKNOWN_CHANNEL("unknown_channel"),
    @SerializedName("channel_inactive")
    CHANNEL_INACTIVE("channel_inactive");

    override fun toString() = value
}