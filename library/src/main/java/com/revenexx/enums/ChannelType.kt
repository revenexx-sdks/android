package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ChannelType(val value: String) {
    @SerializedName("storefront")
    STOREFRONT("storefront"),
    @SerializedName("punchout")
    PUNCHOUT("punchout"),
    @SerializedName("marketplace")
    MARKETPLACE("marketplace"),
    @SerializedName("api")
    API("api"),
    @SerializedName("pos")
    POS("pos");

    override fun toString() = value
}