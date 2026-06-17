package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class UseCases(val value: String) {
    @SerializedName("starter")
    STARTER("starter"),
    @SerializedName("databases")
    DATABASES("databases"),
    @SerializedName("ai")
    AI("ai"),
    @SerializedName("messaging")
    MESSAGING("messaging"),
    @SerializedName("utilities")
    UTILITIES("utilities"),
    @SerializedName("dev-tools")
    DEV_TOOLS("dev-tools"),
    @SerializedName("auth")
    AUTH("auth");

    override fun toString() = value
}