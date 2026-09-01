package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value. `items` is replaced wholesale when sent.
 */
data class MenuUpdateRequest<T>(
    /**
     * The ordered navigation tree. Replaces the stored one completely.
     */
    @SerializedName("items")
    var items: List<PageMenuItem<T>>?,

    /**
     * What this menu is called for the people who edit it.
     */
    @SerializedName("label")
    var label: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
        "label" to label as Any,
    )

    companion object {
        operator fun invoke(
            items: List<PageMenuItem<Map<String, Any>>>?,
            label: String?,
        ) = MenuUpdateRequest<Map<String, Any>>(
            items,
            label,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = MenuUpdateRequest<T>(
            items = (map["items"] as List<Map<String, Any>>).map { PageMenuItem.from(map = it, nestedType) },
            label = map["label"] as? String,
        )
    }
}