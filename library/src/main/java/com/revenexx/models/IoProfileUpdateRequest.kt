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
     * What an import does with the lines the target cart already has: 'replace' clears them first, 'insert' and 'append' both add and behave identically today. Read only when the import names a target_cart_id. Default 'insert'.
     */
    @SerializedName("apply_mode")
    var apply_mode: CartIoApplyMode?,

    /**
     * Which way this profile runs. A profile only ever runs in the direction it declares: handing an import profile to carts.export is a 400, and the other way round.
     */
    @SerializedName("direction")
    var direction: CartIoDirection?,

    /**
     * What the profile carries: whole carts (the `{cart, items}` document) or bare cart lines. Default 'carts'.
     */
    @SerializedName("entity")
    var entity: CartIoEntity?,

    /**
     * The wire format. 'json' is the canonical, re-importable document; 'csv' is the spreadsheet form, and only line fields survive it. Default 'json'.
     */
    @SerializedName("format")
    var format: CartIoFormat?,

    /**
     * One of the bundled templates. Set by carts.io.profiles.defaults; a profile a merchant writes is not one.
     */
    @SerializedName("is_template")
    var is_template: Boolean?,

    /**
     * Baseline-IO-compatible column mapping. An empty object (or null) is identity: the full canonical shape, every field under its own name.
     */
    @SerializedName("mapping")
    var mapping: CartIoMapping?,

    /**
     * What a merchant picks this profile by. Unique within the tenant — reusing a name is a 409.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Free-form options carried with the profile. The four bundled templates put one human sentence under `description` and nothing else; no other key is read by this app, so anything a merchant needs alongside a profile can live here.
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
        "mapping" to mapping?.toMap() as Any,
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
            mapping = CartIoMapping.from(map = map["mapping"] as Map<String, Any>),
            name = map["name"] as? String,
            options = map["options"] as? Any,
        )
    }
}