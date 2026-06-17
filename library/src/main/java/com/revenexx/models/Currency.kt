package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Currency
 */
data class Currency(
    /**
     * Currency code in [ISO 4217-1](http://en.wikipedia.org/wiki/ISO_4217) three-character format.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Number of decimal digits.
     */
    @SerializedName("decimalDigits")
    val decimalDigits: Long,

    /**
     * Currency name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Currency plural name
     */
    @SerializedName("namePlural")
    val namePlural: String,

    /**
     * Currency digit rounding.
     */
    @SerializedName("rounding")
    val rounding: Double,

    /**
     * Currency symbol.
     */
    @SerializedName("symbol")
    val symbol: String,

    /**
     * Currency native symbol.
     */
    @SerializedName("symbolNative")
    val symbolNative: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "decimalDigits" to decimalDigits as Any,
        "name" to name as Any,
        "namePlural" to namePlural as Any,
        "rounding" to rounding as Any,
        "symbol" to symbol as Any,
        "symbolNative" to symbolNative as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Currency(
            code = map["code"] as String,
            decimalDigits = (map["decimalDigits"] as Number).toLong(),
            name = map["name"] as String,
            namePlural = map["namePlural"] as String,
            rounding = (map["rounding"] as Number).toDouble(),
            symbol = map["symbol"] as String,
            symbolNative = map["symbolNative"] as String,
        )
    }
}