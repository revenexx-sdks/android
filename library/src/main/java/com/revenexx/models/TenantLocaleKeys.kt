package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One locale somewhere in this tenant, its read and write keys, and the markets that asked for it.
 */
data class TenantLocaleKeys(
    /**
     * The locale this entry is about, as some market registered it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Its language part, which is also the key under language granularity.
     */
    @SerializedName("language")
    var language: String?,

    /**
     * Codes of the markets that registered this locale, sorted — who a baseline translation written here is actually for. An editor that lists six inputs without saying who needs them invites translations nobody will ever read.
     */
    @SerializedName("markets")
    var markets: List<String>?,

    /**
     * Keys to try in order until one holds text — the same resolved order the per-market answer gives, so a baseline value and a market value can never be keyed differently.
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
        "markets" to markets as Any,
        "read" to read as Any,
        "write" to write as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TenantLocaleKeys(
            code = map["code"] as? String,
            language = map["language"] as? String,
            markets = map["markets"] as? List<String>,
            read = map["read"] as? List<String>,
            write = map["write"] as? String,
        )
    }
}