package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelUnassignedVisibility(val value: String) {
    @SerializedName("inherit")
    INHERIT("inherit"),
    @SerializedName("all")
    ALL("all"),
    @SerializedName("assigned_only")
    ASSIGNED_ONLY("assigned_only");

    override fun toString() = value
}