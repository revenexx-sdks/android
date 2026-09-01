package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderListItem(
    /**
     * The catalogue category the article sat in when the position was saved, as a slug. Kept so a long list can be grouped the way the shop groups it without a call to the catalogue.
     */
    @SerializedName("category_slug")
    var category_slug: String?,

    /**
     * The cost centre this position books to, as the tenant's ERP names it. Free text and not our enum. It survives into the ORDER position, which has a `cost_center` column; a CART line has none, so the cart conversion carries it in the line snapshot instead.
     */
    @SerializedName("cost_center_id")
    var cost_center_id: String?,

    /**
     * When the position was added to the list.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The buyer's OWN article number for this article — what their purchasing system calls it, which is rarely what the shop calls it. Free text, and the field a B2B buyer searches their own lists by.
     */
    @SerializedName("custom_sku")
    var custom_sku: String?,

    /**
     * The position, by id.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The article image at the time the position was saved, as a URL or a path — a snapshot like `name`, and nothing here refreshes it. It rides into the cart line and the order position in their snapshot, because neither has a column for it.
     */
    @SerializedName("image")
    var image: String?,

    /**
     * The list this position belongs to. Taken from the path and never from the payload — a position does not move between lists.
     */
    @SerializedName("list_id")
    var list_id: String?,

    /**
     * Free-form data the tenant keeps on the position. Never read by this app; it travels into the cart line / order position snapshot untouched. A write replaces the whole document rather than merging into it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The article name AS IT WAS when the position was saved. A snapshot on purpose: the list is the buyer's own record, so a renamed or withdrawn article still reads the way they wrote it down.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order within the list, ascending — the order the positions collection returns by default and the order the conversions hand the lines over in. Neither dense nor unique: an add with no `position` of its own takes the list's current position COUNT, so removing a position from the middle and adding another leaves two rows sharing a number. A bulk replace assigns the array index the same way, so it renumbers only the positions it is not given explicitly.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Per-position notes the buyer wrote — an engraving, a delivery instruction, a reference for the picker. An ARRAY OF STRINGS, one entry per line; the order conversion joins them with newlines into the order position's single `position_text`, and the cart conversion carries the array in the line snapshot.
     */
    @SerializedName("position_texts")
    var position_texts: Any?,

    /**
     * Unit price snapshot — what the buyer saw when they saved the position, in whatever way the catalogue quoted it. It is a record, not a live price: the cart and the order reprice on their own terms, so this never becomes what somebody is charged.
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * The catalogue product this position stands for. One of `product_id` / `sku` must be set (the database enforces it); this is the identity the products app answers to, and the one `reject_unknown_articles` and the conversions check against.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much of the article the list holds. Greater than zero — the database refuses the rest — and fractional to three decimals, because a B2B position may be 2.5 metres or 0.75 kilos.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The article number as the catalogue knows it — the alternative identity to `product_id`, and the one an ERP integration usually joins on.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The catalogue subcategory, as a slug. Same purpose as `category_slug`, one level down.
     */
    @SerializedName("subcategory_slug")
    var subcategory_slug: String?,

    /**
     * The VAT rate that applied when the position was saved, as a PERCENT (19 = 19 %). Four decimals so a rate like 8.25 % survives; carts and orders document the same field the same way, and the conversion forwards the number unchanged.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * The tenant this row belongs to, as a slug. Set by the platform, never by a caller — it is the row-level security scope, not a field, and every row a request can reach is inside it already.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * The unit `quantity` counts in, in the tenant's own words. Deliberately open text and deliberately NOT a vocabulary: a B2B catalogue units in pieces, metres, kilos, rolls and pallets, and any closed list published here would be a guess.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * When the position was last changed.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "category_slug" to category_slug as Any,
        "cost_center_id" to cost_center_id as Any,
        "created_at" to created_at as Any,
        "custom_sku" to custom_sku as Any,
        "id" to id as Any,
        "image" to image as Any,
        "list_id" to list_id as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "position_texts" to position_texts as Any,
        "price" to price as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "subcategory_slug" to subcategory_slug as Any,
        "tax_rate" to tax_rate as Any,
        "tenant_id" to tenant_id as Any,
        "unit" to unit as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListItem(
            category_slug = map["category_slug"] as? String,
            cost_center_id = map["cost_center_id"] as? String,
            created_at = map["created_at"] as? String,
            custom_sku = map["custom_sku"] as? String,
            id = map["id"] as? String,
            image = map["image"] as? String,
            list_id = map["list_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            position_texts = map["position_texts"] as? Any,
            price = (map["price"] as? Number)?.toDouble(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            subcategory_slug = map["subcategory_slug"] as? String,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            tenant_id = map["tenant_id"] as? String,
            unit = map["unit"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}