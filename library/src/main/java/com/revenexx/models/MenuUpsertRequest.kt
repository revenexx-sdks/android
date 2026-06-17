package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Create or update the menu identified by menuKey (idempotent per tenant). `items` is the ordered nav tree ([{ label, to, items? }]).
 */
data class MenuUpsertRequest(
    /**
     * Ordered menu entries ({ label, to?, items? }).
     */
    @SerializedName("items")
    var items: List<Any>?,

    /**
     * 
     */
    @SerializedName("label")
    val label: String,

    /**
     * Stable menu identifier, e.g. "main", "footer", "account".
     */
    @SerializedName("menuKey")
    val menuKey: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items as Any,
        "label" to label as Any,
        "menuKey" to menuKey as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MenuUpsertRequest(
            items = map["items"] as? List<Any>,
            label = map["label"] as String,
            menuKey = map["menuKey"] as String,
        )
    }
}