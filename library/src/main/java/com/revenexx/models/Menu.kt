package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Menu(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * 
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("items")
    var items: Any?,

    /**
     * 
     */
    @SerializedName("label")
    var label: String?,

    /**
     * 
     */
    @SerializedName("menu_key")
    var menu_key: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "deleted_at" to deleted_at as Any,
        "id" to id as Any,
        "items" to items as Any,
        "label" to label as Any,
        "menu_key" to menu_key as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Menu(
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            deleted_at = map["deleted_at"] as? String,
            id = map["id"] as? String,
            items = map["items"] as? Any,
            label = map["label"] as? String,
            menu_key = map["menu_key"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}