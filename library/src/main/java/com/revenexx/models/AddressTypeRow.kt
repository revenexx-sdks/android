package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AddressTypeRowTone

/**
 * One value of the address types set. What an address is used for. Billing and shipping are what a checkout needs; a works entrance or a central accounts office is the tenant's own.
 */
data class AddressTypeRow(
    /**
     * What `addresses.type` stores, and the only part of this row other data depends on. Immutable once created: renaming it would orphan every record carrying it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the value was added to this set.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * One line of help for an operator choosing this value. Null when there is nothing to add. A row seeded before 0.22.0 may hold a serialized locale map here instead (PE-443).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * Primary key of this value. What the update and delete routes address it by — the CODE is what records store.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The value a create falls back to when the caller names none. Exactly one row of the set carries it; promoting another one demotes this.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * True for a value this app seeded on install. Still renameable and still removable — it only records where the value came from.
     */
    @SerializedName("is_system")
    var is_system: Boolean?,

    /**
     * Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Where this value sits in the set, ascending. It is the order a select should offer.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * The fallback name — what a client shows when no locale in `labels` matches. A row seeded before 0.22.0 may hold a serialized locale map here instead (PE-443) — those rows were seeded with no `labels` at all.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge colour. The palette stays fixed — it is a render concern, not a merchant decision.
     */
    @SerializedName("tone")
    var tone: AddressTypeRowTone?,

    /**
     * When it was last edited.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "is_system" to is_system as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "tenant_id" to tenant_id as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AddressTypeRow(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            is_system = map["is_system"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            tenant_id = map["tenant_id"] as? String,
            title = map["title"] as? String,
            tone = AddressTypeRowTone.values().find { it.value == (map["tone"] as? String) } ?: null,
            updated_at = map["updated_at"] as? String,
        )
    }
}