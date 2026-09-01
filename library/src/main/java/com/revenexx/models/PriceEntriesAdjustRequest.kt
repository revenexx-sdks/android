package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEndingRule

/**
 * Change every priced entry of a list at once. Send 'percent' OR 'amount', never both. On-request entries are never touched — a percentage of "ask us" is not a number.
 */
data class PriceEntriesAdjustRequest(
    /**
     * Absolute change added to every unit price, in the list's currency.
     */
    @SerializedName("amount")
    var amount: Double?,

    /**
     * true writes nothing and answers the same preview — what the Cockpit dialog shows before it commits.
     */
    @SerializedName("dry_run")
    var dry_run: Boolean?,

    /**
     * Relative change in percent: 5 raises by 5 %, -10 cuts by 10 %.
     */
    @SerializedName("percent")
    var percent: Double?,

    /**
     * Ending the computed prices snap to (nearest match). Omit to use the tenant's bulk_adjust_rounding setting.
     */
    @SerializedName("rounding")
    var rounding: PriceEndingRule?,

    /**
     * Restrict the change to entries whose SKU starts with this (a prefix, case-sensitive, no wildcards). Entries identified only by product_id never match a prefix. Omit to change the whole list.
     */
    @SerializedName("sku_prefix")
    var sku_prefix: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "amount" to amount as Any,
        "dry_run" to dry_run as Any,
        "percent" to percent as Any,
        "rounding" to rounding?.value as Any,
        "sku_prefix" to sku_prefix as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesAdjustRequest(
            amount = (map["amount"] as? Number)?.toDouble(),
            dry_run = map["dry_run"] as? Boolean,
            percent = (map["percent"] as? Number)?.toDouble(),
            rounding = PriceEndingRule.values().find { it.value == (map["rounding"] as? String) } ?: null,
            sku_prefix = map["sku_prefix"] as? String,
        )
    }
}