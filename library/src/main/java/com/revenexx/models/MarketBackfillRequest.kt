package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The path id is the market being REPAIRED; `source` is the market to copy from (a uuid or a market code). The three flags default to true.
 */
data class MarketBackfillRequest(
    /**
     * Take the source's traded currencies for codes this market does not already carry. Default true.
     */
    @SerializedName("currencies")
    var currencies: Boolean?,

    /**
     * Take the source's locales for codes this market does not already carry. Default true.
     */
    @SerializedName("locales")
    var locales: Boolean?,

    /**
     * The market to copy the missing pieces FROM — a uuid or a market code. Must not be the market in the path. Pick a market that is already right; nothing about it is changed.
     */
    @SerializedName("source")
    val source: String,

    /**
     * Take the source's tax classes for codes this market does not already carry. An existing code keeps ITS rate — a backfill never re-rates a class the merchant already set. Default true.
     */
    @SerializedName("tax_classes")
    var tax_classes: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies as Any,
        "locales" to locales as Any,
        "source" to source as Any,
        "tax_classes" to tax_classes as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketBackfillRequest(
            currencies = map["currencies"] as? Boolean,
            locales = map["locales"] as? Boolean,
            source = map["source"] as String,
            tax_classes = map["tax_classes"] as? Boolean,
        )
    }
}