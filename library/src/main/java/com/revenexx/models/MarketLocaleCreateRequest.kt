package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The owning market comes from the route path ('market_id').
 */
data class MarketLocaleCreateRequest(
    /**
     * Locale code, e.g. 'de-DE' (unique per market).
     */
    @SerializedName("code")
    val code: String,

    /**
     * ISO 3166-1 alpha-2 country code.
     */
    @SerializedName("country")
    val country: String,

    /**
     * 
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * ISO 639-1 language code.
     */
    @SerializedName("language")
    val language: String,

    /**
     * Sort position (default 0).
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "country" to country as Any,
        "is_default" to is_default as Any,
        "language" to language as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketLocaleCreateRequest(
            code = map["code"] as String,
            country = map["country"] as String,
            is_default = map["is_default"] as? Boolean,
            language = map["language"] as String,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}