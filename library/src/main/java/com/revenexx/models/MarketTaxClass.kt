package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One rate bucket within a market — 'standard', 'reduced', 'zero' — and the source of record for that rate across the platform. Other apps point at it by CODE, with no foreign key behind it.
 */
data class MarketTaxClass(
    /**
     * Tax class code, unique per market — the rate bucket a product or a shipping method is assigned to ('standard', 'reduced', 'zero'). Other apps name a class by THIS and by nothing else: there is no foreign key behind it and there cannot be (ADR-0055), which is why the delete route asks the shipping app what still points at the code before removing it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the tax class was created on this market. Set by the database; never writable.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of this tax class. The class is named by `code` everywhere else, including by other apps.
     */
    @SerializedName("id")
    var id: String?,

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
     * The market this tax class belongs to. Filled from the route path on write and never read out of the body; ON DELETE CASCADE, so deleting the market deletes this row.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Display name of the rate bucket, in the operator's own language.
     */
    @SerializedName("name")
    var name: String?,

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

    /**
     * When the tax class was last written. Set by the database on every update; never writable.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "market_id" to market_id as Any,
        "name" to name as Any,
        "position" to position as Any,
        "rate" to rate as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketTaxClass(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            market_id = map["market_id"] as? String,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            rate = (map["rate"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
        )
    }
}