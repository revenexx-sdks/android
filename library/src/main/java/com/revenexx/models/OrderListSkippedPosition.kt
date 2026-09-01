package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A position left out of the conversion because the catalogue no longer knows its article (only ever non-empty when the tenant's 'on_missing_article' setting is 'skip').
 */
data class OrderListSkippedPosition(
    /**
     * The position that was left out, so a client can point at the row in the list.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The saved article name, so the omission can be reported to the buyer in words they recognise.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The catalogue product the position named, if it named one.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The article number the position named, if it named one.
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "name" to name as Any,
        "product_id" to product_id as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListSkippedPosition(
            id = map["id"] as? String,
            name = map["name"] as? String,
            product_id = map["product_id"] as? String,
            sku = map["sku"] as? String,
        )
    }
}