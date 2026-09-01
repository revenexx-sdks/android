package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class EntityType(val value: String) {
    @SerializedName("product")
    PRODUCT("product"),
    @SerializedName("reference_entity")
    REFERENCE_ENTITY("reference_entity"),
    @SerializedName("asset")
    ASSET("asset"),
    @SerializedName("category")
    CATEGORY("category");

    override fun toString() = value
}