package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelVisibilityReason(val value: String) {
    @SerializedName("assigned")
    ASSIGNED("assigned"),
    @SerializedName("not_assigned_to_channel")
    NOT_ASSIGNED_TO_CHANNEL("not_assigned_to_channel"),
    @SerializedName("unassigned_open")
    UNASSIGNED_OPEN("unassigned_open"),
    @SerializedName("unassigned_closed")
    UNASSIGNED_CLOSED("unassigned_closed"),
    @SerializedName("no_channel_context")
    NO_CHANNEL_CONTEXT("no_channel_context");

    override fun toString() = value
}