package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One navigation menu of the tenant, addressed by the stable key a theme looks it up under.
 */
data class Menu<T>(
    /**
     * When the menu was created.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The user id that created the menu.
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * The tombstone. A soft-deleted menu disappears from the renderer immediately.
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * The menu row id. Used by the management routes; the renderer addresses a menu by its `menu_key` instead, because that is the thing a theme hard-codes.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The ordered navigation tree itself. Stored exactly as it was sent, so the theme and the editor agree on the shape without this app enforcing one.
     */
    @SerializedName("items")
    var items: List<PageMenuItem<T>>?,

    /**
     * What this menu is called for the people who edit it. Never rendered in the storefront.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The stable name the theme asks for a menu by — `main`, `footer`, `account`. It is what makes seeding idempotent and what a header component looks up; renaming it detaches the menu from the theme slot.
     */
    @SerializedName("menu_key")
    var menu_key: String?,

    /**
     * When the menu was last replaced. The upsert rewrites `items` wholesale, so this is the timestamp of the whole navigation, not of one entry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "deleted_at" to deleted_at as Any,
        "id" to id as Any,
        "items" to items?.map { it.toMap() } as Any,
        "label" to label as Any,
        "menu_key" to menu_key as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {
        operator fun invoke(
            created_at: String?,
            created_by: String?,
            deleted_at: String?,
            id: String?,
            items: List<PageMenuItem<Map<String, Any>>>?,
            label: String?,
            menu_key: String?,
            updated_at: String?,
        ) = Menu<Map<String, Any>>(
            created_at,
            created_by,
            deleted_at,
            id,
            items,
            label,
            menu_key,
            updated_at,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = Menu<T>(
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            deleted_at = map["deleted_at"] as? String,
            id = map["id"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { PageMenuItem.from(map = it, nestedType) },
            label = map["label"] as? String,
            menu_key = map["menu_key"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}