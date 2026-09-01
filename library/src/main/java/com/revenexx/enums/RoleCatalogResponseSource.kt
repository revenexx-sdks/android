package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class RoleCatalogResponseSource(val value: String) {
    @SerializedName("tenant")
    TENANT("tenant"),
    @SerializedName("defaults")
    DEFAULTS("defaults");

    override fun toString() = value
}