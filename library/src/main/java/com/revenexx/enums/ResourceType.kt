package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ResourceType(val value: String) {
    @SerializedName("template")
    TEMPLATE("template"),
    @SerializedName("layout")
    LAYOUT("layout"),
    @SerializedName("suppression")
    SUPPRESSION("suppression");

    override fun toString() = value
}