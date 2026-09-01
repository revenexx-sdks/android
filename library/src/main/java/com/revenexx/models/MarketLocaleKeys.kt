package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The read and write keys for one of the market's locales, already resolved from the two settings.
 */
data class MarketLocaleKeys(
    /**
     * The market's locale this entry is about.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Its language part, which is also the key under language granularity.
     */
    @SerializedName("language")
    var language: String?,

    /**
     * Keys to try in order until one holds text. Always starts at the exact code: a fallback fills a gap, it never outranks a stored value.
     */
    @SerializedName("read")
    var read: List<String>?,

    /**
     * A key inside a labels bag: a full locale ('de-DE') under regional granularity, a bare language ('de') under language granularity.
     */
    @SerializedName("write")
    var write: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "language" to language as Any,
        "read" to read as Any,
        "write" to write as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketLocaleKeys(
            code = map["code"] as? String,
            language = map["language"] as? String,
            read = map["read"] as? List<String>,
            write = map["write"] as? String,
        )
    }
}