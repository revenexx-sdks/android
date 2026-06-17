package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class LocationType(val value: String) {
    @SerializedName("warehouse")
    WAREHOUSE("warehouse"),
    @SerializedName("store")
    STORE("store"),
    @SerializedName("dropship")
    DROPSHIP("dropship"),
    @SerializedName("virtual")
    VIRTUAL("virtual");

    override fun toString() = value
}