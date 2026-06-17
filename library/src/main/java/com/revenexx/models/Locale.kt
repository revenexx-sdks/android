package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Locale
 */
data class Locale(
    /**
     * Continent name. This field support localization.
     */
    @SerializedName("continent")
    val continent: String,

    /**
     * Continent code. A two character continent code "AF" for Africa, "AN" for Antarctica, "AS" for Asia, "EU" for Europe, "NA" for North America, "OC" for Oceania, and "SA" for South America.
     */
    @SerializedName("continentCode")
    val continentCode: String,

    /**
     * Country name. This field support localization.
     */
    @SerializedName("country")
    val country: String,

    /**
     * Country code in [ISO 3166-1](http://en.wikipedia.org/wiki/ISO_3166-1) two-character format
     */
    @SerializedName("countryCode")
    val countryCode: String,

    /**
     * Currency code in [ISO 4217-1](http://en.wikipedia.org/wiki/ISO_4217) three-character format
     */
    @SerializedName("currency")
    val currency: String,

    /**
     * True if country is part of the European Union.
     */
    @SerializedName("eu")
    val eu: Boolean,

    /**
     * User IP address.
     */
    @SerializedName("ip")
    val ip: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "continent" to continent as Any,
        "continentCode" to continentCode as Any,
        "country" to country as Any,
        "countryCode" to countryCode as Any,
        "currency" to currency as Any,
        "eu" to eu as Any,
        "ip" to ip as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Locale(
            continent = map["continent"] as String,
            continentCode = map["continentCode"] as String,
            country = map["country"] as String,
            countryCode = map["countryCode"] as String,
            currency = map["currency"] as String,
            eu = map["eu"] as Boolean,
            ip = map["ip"] as String,
        )
    }
}