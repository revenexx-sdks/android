package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartIoApplyMode
import com.revenexx.enums.CartIoDirection
import com.revenexx.enums.CartIoEntity
import com.revenexx.enums.CartIoFormat

/**
 * Partial update — omitted fields keep their current value.
 */
data class IoProfileUpdateRequest(
    /**
     * Default 'insert'.
     */
    @SerializedName("apply_mode")
    var apply_mode: CartIoApplyMode?,

    /**
     * 
     */
    @SerializedName("direction")
    var direction: CartIoDirection?,

    /**
     * Default 'carts'.
     */
    @SerializedName("entity")
    var entity: CartIoEntity?,

    /**
     * Default 'json'.
     */
    @SerializedName("format")
    var format: CartIoFormat?,

    /**
     * 
     */
    @SerializedName("is_template")
    var is_template: Boolean?,

    /**
     * Column mapping (Baseline-IO-compatible).
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

) {
    fun toMap(): Map<String, Any> = mapOf(
        "apply_mode" to apply_mode?.value as Any,
        "direction" to direction?.value as Any,
        "entity" to entity?.value as Any,
        "format" to format?.value as Any,
        "is_template" to is_template as Any,
        "mapping" to mapping as Any,
        "name" to name as Any,
        "options" to options as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IoProfileUpdateRequest(
            apply_mode = CartIoApplyMode.values().find { it.value == (map["apply_mode"] as? String) } ?: null,
            direction = CartIoDirection.values().find { it.value == (map["direction"] as? String) } ?: null,
            entity = CartIoEntity.values().find { it.value == (map["entity"] as? String) } ?: null,
            format = CartIoFormat.values().find { it.value == (map["format"] as? String) } ?: null,
            is_template = map["is_template"] as? Boolean,
            mapping = map["mapping"] as? Any,
            name = map["name"] as? String,
            options = map["options"] as? Any,
        )
    }
}