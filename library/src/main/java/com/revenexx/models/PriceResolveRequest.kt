package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Buyer context + items. Unpriceable items come back as on_request — a missing price is a first-class state, never 0.
 */
data class PriceResolveRequest(
    /**
     * The instant every validity window — list and entry — is evaluated at (ISO 8601). Default now. This is how a promo price is previewed before it starts, and it is echoed as `basis.evaluated_at`.
     */
    @SerializedName("at")
    var at: String?,

    /**
     * Buyer context: the sales channel. Third scope — beats the open lists, loses to contact and organization.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Buyer context: the contact this quote is for. The most specific scope — a list naming this contact beats every other list, whatever their priority. Sending it (or organization_id) is also what makes the buyer AUTHENTICATED for `requires_auth` lists and for the tenant’s anonymous_resolve_allowed setting.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code the quote is wanted in. ONLY lists in this currency are candidates and nothing is ever converted, so a wrong value here is not a rounding difference — it is no price at all. Omit to take the buyer market’s currency, then the tenant’s default_currency; `basis.currency_source` names which applied.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Items to price, at most 200 per call — a whole cart or a whole product listing in one round trip. The answer holds one entry per item, in this order.
     */
    @SerializedName("items")
    val items: List<PriceResolveItem>,

    /**
     * Buyer context: the market, as a uuid pin for older callers. Prefer the `X-Revenexx-Market` header, which carries a market CODE and is what scopes the visible price lists. The market decides the tax rates AND which per-market settings (rounding, tie-break, anonymous access) apply — with several markets and no signal at all the answer says `tax.resolved: false`, `reason: market_required` rather than quoting another market’s VAT.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Buyer context: the organization the buyer belongs to. Second most specific scope; also counts as authenticated.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "at" to at as Any,
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "currency" to currency as Any,
        "items" to items.map { it.toMap() } as Any,
        "market_id" to market_id as Any,
        "organization_id" to organization_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceResolveRequest(
            at = map["at"] as? String,
            channel_id = map["channel_id"] as? String,
            contact_id = map["contact_id"] as? String,
            currency = map["currency"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { PriceResolveItem.from(map = it) },
            market_id = map["market_id"] as? String,
            organization_id = map["organization_id"] as? String,
        )
    }
}