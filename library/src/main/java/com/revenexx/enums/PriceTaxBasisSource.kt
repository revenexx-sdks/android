package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class PriceTaxBasisSource(val value: String) {
    @SerializedName("list")
    LIST("list"),
    @SerializedName("list_legacy")
    LIST_LEGACY("list_legacy"),
    @SerializedName("tenant")
    TENANT("tenant");

    override fun toString() = value
}