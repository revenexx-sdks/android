package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class AttributeValueBucket(val value: String) {
    @SerializedName("common")
    COMMON("common"),
    @SerializedName("locale_specific")
    LOCALE_SPECIFIC("locale_specific"),
    @SerializedName("channel_specific")
    CHANNEL_SPECIFIC("channel_specific"),
    @SerializedName("channel_locale_specific")
    CHANNEL_LOCALE_SPECIFIC("channel_locale_specific");

    override fun toString() = value
}