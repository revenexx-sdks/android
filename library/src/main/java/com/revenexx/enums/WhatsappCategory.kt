package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class WhatsappCategory(val value: String) {
    @SerializedName("marketing")
    MARKETING("marketing"),
    @SerializedName("utility")
    UTILITY("utility"),
    @SerializedName("authentication")
    AUTHENTICATION("authentication"),
    @SerializedName("service")
    SERVICE("service");

    override fun toString() = value
}