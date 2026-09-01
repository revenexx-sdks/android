package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class AvatarsGetCreditCardCode(val value: String) {
    @SerializedName("amex")
    AMEX("amex"),
    @SerializedName("argencard")
    ARGENCARD("argencard"),
    @SerializedName("cabal")
    CABAL("cabal"),
    @SerializedName("cencosud")
    CENCOSUD("cencosud"),
    @SerializedName("diners")
    DINERS("diners"),
    @SerializedName("discover")
    DISCOVER("discover"),
    @SerializedName("elo")
    ELO("elo"),
    @SerializedName("hipercard")
    HIPERCARD("hipercard"),
    @SerializedName("jcb")
    JCB("jcb"),
    @SerializedName("mastercard")
    MASTERCARD("mastercard"),
    @SerializedName("naranja")
    NARANJA("naranja"),
    @SerializedName("targeta-shopping")
    TARGETA_SHOPPING("targeta-shopping"),
    @SerializedName("unionpay")
    UNIONPAY("unionpay"),
    @SerializedName("visa")
    VISA("visa"),
    @SerializedName("mir")
    MIR("mir"),
    @SerializedName("maestro")
    MAESTRO("maestro"),
    @SerializedName("rupay")
    RUPAY("rupay");

    override fun toString() = value
}