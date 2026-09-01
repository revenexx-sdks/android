package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelPolicyTenantDefault(val value: String) {
    @SerializedName("all")
    ALL("all"),
    @SerializedName("assigned_only")
    ASSIGNED_ONLY("assigned_only");

    override fun toString() = value
}