package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The owning market comes from the route path ('market_id').
 */
data class MarketTaxClassCreateRequest(
    /**
     * Tax class code, unique per market — the rate bucket a product or a shipping method is assigned to ('standard', 'reduced', 'zero'). Other apps name a class by THIS and by nothing else: there is no foreign key behind it and there cannot be (ADR-0055), which is why the delete route asks the shipping app what still points at the code before removing it.
     */
    @SerializedName("code")
    val code: String,

    /**
     * The class applied to a line that names none. At most one per market. A market that stores GROSS prices and marks no default cannot break those prices back down into net, which is why readiness turns that combination from a warning into a blocking failure.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized display names for storefronts and invoices, keyed by locale: a flat {locale: label} map, one level deep, string values. The key to write is the `locale_policy.write` from GET /markets/{id}/context, exactly as for a market's labels. Null means nothing is translated and `name` is all there is.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Display name of the rate bucket, in the operator's own language.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Sort position among this market's tax classes, ascending, default 0 — and the tie-break that picks a class when none is flagged default.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Tax rate in PERCENT, 0–100 (default 0) — 20 means 20 %, not 0.2. Whether a stored price already contains it is a separate question, answered per market by `pricing.tax_basis` on the context.
     */
    @SerializedName("rate")
    var rate: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "rate" to rate as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketTaxClassCreateRequest(
            code = map["code"] as String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as String,
            position = (map["position"] as? Number)?.toLong(),
            rate = (map["rate"] as? Number)?.toDouble(),
        )
    }
}