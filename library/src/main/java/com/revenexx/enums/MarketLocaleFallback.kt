package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class MarketLocaleFallback(val value: String) {
    @SerializedName("language")
    LANGUAGE("language"),
    @SerializedName("default_locale")
    DEFAULT_LOCALE("default_locale"),
    @SerializedName("none")
    NONE("none");

    override fun toString() = value
}