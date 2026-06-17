package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class IoProfile(
    /**
     * 
     */
    @SerializedName("apply_mode")
    var apply_mode: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("direction")
    var direction: String?,

    /**
     * 
     */
    @SerializedName("entity")
    var entity: String?,

    /**
     * 
     */
    @SerializedName("format")
    var format: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_template")
    var is_template: Boolean?,

    /**
     * 
     */
    @SerializedName("mapping")
    var mapping: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "apply_mode" to apply_mode as Any,
        "created_at" to created_at as Any,
        "direction" to direction as Any,
        "entity" to entity as Any,
        "format" to format as Any,
        "id" to id as Any,
        "is_template" to is_template as Any,
        "mapping" to mapping as Any,
        "name" to name as Any,
        "options" to options as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IoProfile(
            apply_mode = map["apply_mode"] as? String,
            created_at = map["created_at"] as? String,
            direction = map["direction"] as? String,
            entity = map["entity"] as? String,
            format = map["format"] as? String,
            id = map["id"] as? String,
            is_template = map["is_template"] as? Boolean,
            mapping = map["mapping"] as? Any,
            name = map["name"] as? String,
            options = map["options"] as? Any,
            updated_at = map["updated_at"] as? String,
        )
    }
}