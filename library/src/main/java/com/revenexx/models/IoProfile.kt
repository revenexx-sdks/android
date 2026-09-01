package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartIoApplyMode
import com.revenexx.enums.CartIoDirection
import com.revenexx.enums.CartIoEntity
import com.revenexx.enums.CartIoFormat

/**
 * 
 */
data class IoProfile(
    /**
     * What an import does with the lines the target cart already has. 'replace' clears them first; 'insert' and 'append' both add, and behave identically today. Read only by carts.import, and only when the call names a target_cart_id — an import that creates its own cart has nothing to apply a mode to.
     */
    @SerializedName("apply_mode")
    var apply_mode: CartIoApplyMode?,

    /**
     * When the profile was created — for the bundled templates, when the app was installed.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Which way this profile runs. A profile only ever runs in the direction it declares: handing an import profile to carts.export is a 400, and the other way round.
     */
    @SerializedName("direction")
    var direction: CartIoDirection?,

    /**
     * What the profile carries: whole carts ('carts' — the `{cart, items}` document) or bare cart lines ('cart_items' — the spreadsheet a buyer quick-orders from).
     */
    @SerializedName("entity")
    var entity: CartIoEntity?,

    /**
     * The wire format. 'json' is the canonical, re-importable document; 'csv' is the spreadsheet form, and only line fields survive it.
     */
    @SerializedName("format")
    var format: CartIoFormat?,

    /**
     * The profile, as carts.export and carts.import name it in `profile_id`.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * One of the profiles this app ships with, seeded by carts.io.profiles.defaults. A profile a merchant wrote is not one, so this is how a UI separates "what came with the app" from "what we built".
     */
    @SerializedName("is_template")
    var is_template: Boolean?,

    /**
     * Baseline-IO-compatible column mapping. An empty object (or null) is identity: the full canonical shape, every field under its own name.
     */
    @SerializedName("mapping")
    var mapping: CartIoMapping?,

    /**
     * What a merchant picks this profile by. Unique within the tenant — reusing a name is a 409 — and the four bundled templates use it as their identity, so seeding is idempotent by name.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Free-form options carried with the profile. The four bundled templates put one human sentence under `description` and nothing else; no other key is read by this app, so anything a merchant needs alongside a profile can live here.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * The tenant this row belongs to, echoed by the data plane.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When the profile last changed.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "apply_mode" to apply_mode?.value as Any,
        "created_at" to created_at as Any,
        "direction" to direction?.value as Any,
        "entity" to entity?.value as Any,
        "format" to format?.value as Any,
        "id" to id as Any,
        "is_template" to is_template as Any,
        "mapping" to mapping?.toMap() as Any,
        "name" to name as Any,
        "options" to options as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IoProfile(
            apply_mode = CartIoApplyMode.values().find { it.value == (map["apply_mode"] as? String) } ?: null,
            created_at = map["created_at"] as? String,
            direction = CartIoDirection.values().find { it.value == (map["direction"] as? String) } ?: null,
            entity = CartIoEntity.values().find { it.value == (map["entity"] as? String) } ?: null,
            format = CartIoFormat.values().find { it.value == (map["format"] as? String) } ?: null,
            id = map["id"] as? String,
            is_template = map["is_template"] as? Boolean,
            mapping = CartIoMapping.from(map = map["mapping"] as Map<String, Any>),
            name = map["name"] as? String,
            options = map["options"] as? Any,
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}