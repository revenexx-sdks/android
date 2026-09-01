package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Create or replace the menu identified by menuKey (idempotent per tenant). `items` is written wholesale — there is no per-entry edit, so send the whole tree every time.
 */
data class MenuUpsertRequest<T>(
    /**
     * The ordered navigation tree. Replaces the stored one completely.
     */
    @SerializedName("items")
    var items: List<PageMenuItem<T>>?,

    /**
     * What this menu is called for the people who edit it. Required on a create; an update keeps the label it had when this is left out.
     */
    @SerializedName("label")
    val label: String,

    /**
     * The stable slot the theme asks for this menu by. Idempotency is keyed on it: sending an existing key replaces that menu instead of creating a second one.
     */
    @SerializedName("menuKey")
    val menuKey: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
        "label" to label as Any,
        "menuKey" to menuKey as Any,
    )

    companion object {
        operator fun invoke(
            items: List<PageMenuItem<Map<String, Any>>>?,
            label: String,
            menuKey: String,
        ) = MenuUpsertRequest<Map<String, Any>>(
            items,
            label,
            menuKey,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = MenuUpsertRequest<T>(
            items = (map["items"] as List<Map<String, Any>>).map { PageMenuItem.from(map = it, nestedType) },
            label = map["label"] as String,
            menuKey = map["menuKey"] as String,
        )
    }
}