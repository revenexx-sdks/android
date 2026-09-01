package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One navigation menu, ready to render.
 */
data class DeliveryMenu<T>(
    /**
     * The menu KEY (`main`, `footer`, `account`), not the row id — this is the handle a theme hard-codes.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The ordered navigation tree, exactly as it is stored. Render it in order; nesting is `items` inside an entry.
     */
    @SerializedName("items")
    var items: List<PageMenuItem<T>>?,

    /**
     * What the menu is called for the people who edit it. A theme rarely renders it.
     */
    @SerializedName("label")
    var label: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "items" to items?.map { it.toMap() } as Any,
        "label" to label as Any,
    )

    companion object {
        operator fun invoke(
            id: String?,
            items: List<PageMenuItem<Map<String, Any>>>?,
            label: String?,
        ) = DeliveryMenu<Map<String, Any>>(
            id,
            items,
            label,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = DeliveryMenu<T>(
            id = map["id"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { PageMenuItem.from(map = it, nestedType) },
            label = map["label"] as? String,
        )
    }
}