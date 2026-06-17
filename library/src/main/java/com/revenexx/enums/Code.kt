package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class Code(val value: String) {
    @SerializedName("aa")
    AA("aa"),
    @SerializedName("an")
    AN("an"),
    @SerializedName("ch")
    CH("ch"),
    @SerializedName("ci")
    CI("ci"),
    @SerializedName("cm")
    CM("cm"),
    @SerializedName("cr")
    CR("cr"),
    @SerializedName("ff")
    FF("ff"),
    @SerializedName("sf")
    SF("sf"),
    @SerializedName("mf")
    MF("mf"),
    @SerializedName("ps")
    PS("ps"),
    @SerializedName("oi")
    OI("oi"),
    @SerializedName("om")
    OM("om"),
    @SerializedName("op")
    OP("op"),
    @SerializedName("on")
    ON("on");

    override fun toString() = value
}