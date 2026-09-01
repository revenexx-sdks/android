package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The second sweep, and the only destructive thing this app does: carts past their retention window are deleted, their lines with them. An ordered cart is never touched at any setting — it is the source record of a sale.
 */
data class CartPurgeSweep(
    /**
     * More carts were available to examine than one pass examines; the rest go next tick, oldest first.
     */
    @SerializedName("capped")
    var capped: Boolean?,

    /**
     * The carts this sweep touched, so a merchant can look at them before or after.
     */
    @SerializedName("cart_ids")
    var cart_ids: List<String>?,

    /**
     * The tenant baseline's window for CUSTOMER carts, in days. 0 is 'never delete' — the default, and also where an unparsable value lands, so no settings outage can start a purge.
     */
    @SerializedName("cart_ttl_days")
    var cart_ttl_days: Double?,

    /**
     * The baseline cutoff, for carts belonging to no market. Null when the baseline keeps everything.
     */
    @SerializedName("cutoff")
    var cutoff: String?,

    /**
     * Carts actually deleted. 0 on a dry run — see `found`.
     */
    @SerializedName("deleted")
    var deleted: Long?,

    /**
     * Retention was in force for at least one cart this pass looked at — the baseline, or some market that sets a window while the baseline leaves it off. False means nothing could have been deleted.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Carts past their retention window. On a dry run this is what the wet run would remove.
     */
    @SerializedName("found")
    var found: Long?,

    /**
     * The same for GUEST carts — a cart with a session key and no contact behind it. Kept separate because the two are worth different amounts: a named B2B cart may be a quote somebody is still thinking about.
     */
    @SerializedName("guest_cart_ttl_days")
    var guest_cart_ttl_days: Double?,

    /**
     * Lines actually deleted with them. 0 on a dry run.
     */
    @SerializedName("items_deleted")
    var items_deleted: Long?,

    /**
     * The market codes this pass came across. Each cart was held against ITS market's window, not the baseline's.
     */
    @SerializedName("markets")
    var markets: List<String>?,

    /**
     * Lines the wet run would remove. Always present, on a wet run too, so a client never has to tell "nothing to delete" apart from "this build did not report it".
     */
    @SerializedName("would_delete_items")
    var would_delete_items: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "capped" to capped as Any,
        "cart_ids" to cart_ids as Any,
        "cart_ttl_days" to cart_ttl_days as Any,
        "cutoff" to cutoff as Any,
        "deleted" to deleted as Any,
        "enabled" to enabled as Any,
        "found" to found as Any,
        "guest_cart_ttl_days" to guest_cart_ttl_days as Any,
        "items_deleted" to items_deleted as Any,
        "markets" to markets as Any,
        "would_delete_items" to would_delete_items as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartPurgeSweep(
            capped = map["capped"] as? Boolean,
            cart_ids = map["cart_ids"] as? List<String>,
            cart_ttl_days = (map["cart_ttl_days"] as? Number)?.toDouble(),
            cutoff = map["cutoff"] as? String,
            deleted = (map["deleted"] as? Number)?.toLong(),
            enabled = map["enabled"] as? Boolean,
            found = (map["found"] as? Number)?.toLong(),
            guest_cart_ttl_days = (map["guest_cart_ttl_days"] as? Number)?.toDouble(),
            items_deleted = (map["items_deleted"] as? Number)?.toLong(),
            markets = map["markets"] as? List<String>,
            would_delete_items = (map["would_delete_items"] as? Number)?.toLong(),
        )
    }
}