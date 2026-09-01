package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class CartPriceSnapshotMode(val value: String) {
    @SerializedName("snapshot")
    SNAPSHOT("snapshot"),
    @SerializedName("live")
    LIVE("live");

    override fun toString() = value
}