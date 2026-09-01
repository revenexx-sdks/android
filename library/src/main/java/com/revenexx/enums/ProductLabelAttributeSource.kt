package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ProductLabelAttributeSource(val value: String) {
    @SerializedName("family")
    FAMILY("family"),
    @SerializedName("setting")
    SETTING("setting"),
    @SerializedName("convention")
    CONVENTION("convention");

    override fun toString() = value
}