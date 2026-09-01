package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.IoProfileResourceApplyMode
import com.revenexx.enums.IoProfileResourceDirection

/**
 * A saved profile. Mirrors the controller's presenter exactly — there
 * are no `created_at` / `updated_at` fields on this resource.
 * 
 */
data class IoProfileResource(
    /**
     * 
     */
    @SerializedName("app")
    var app: String?,

    /**
     * 
     */
    @SerializedName("apply_mode")
    var apply_mode: IoProfileResourceApplyMode?,

    /**
     * 
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * 
     */
    @SerializedName("direction")
    var direction: IoProfileResourceDirection?,

    /**
     * 
     */
    @SerializedName("entity")
    var entity: String?,

    /**
     * 
     */
    @SerializedName("format")
    var format: IoProfileFormat?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("mapping")
    var mapping: Any?,

    /**
     * `null` means global — offered for every market.
     */
    @SerializedName("markets")
    var markets: List<String>?,

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
    @SerializedName("vendor")
    var vendor: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "apply_mode" to apply_mode?.value as Any,
        "created_by" to created_by as Any,
        "direction" to direction?.value as Any,
        "entity" to entity as Any,
        "format" to format?.toMap() as Any,
        "id" to id as Any,
        "mapping" to mapping as Any,
        "markets" to markets as Any,
        "name" to name as Any,
        "options" to options as Any,
        "vendor" to vendor as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IoProfileResource(
            app = map["app"] as? String,
            apply_mode = IoProfileResourceApplyMode.values().find { it.value == (map["apply_mode"] as? String) } ?: null,
            created_by = map["created_by"] as? String,
            direction = IoProfileResourceDirection.values().find { it.value == (map["direction"] as? String) } ?: null,
            entity = map["entity"] as? String,
            format = IoProfileFormat.from(map = map["format"] as Map<String, Any>),
            id = map["id"] as? String,
            mapping = map["mapping"] as? Any,
            markets = map["markets"] as? List<String>,
            name = map["name"] as? String,
            options = map["options"] as? Any,
            vendor = map["vendor"] as? String,
        )
    }
}