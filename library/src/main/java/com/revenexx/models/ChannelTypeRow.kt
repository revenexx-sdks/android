package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelTypeTone

/**
 * 
 */
data class ChannelTypeRow(
    /**
     * What `channels.type` stores. Immutable once created — renaming it would orphan every channel that carries it, and there is no FK behind `channels.type` to cascade. A fresh install seeds storefront, punchout, marketplace, api, pos; a merchant may retire any of them and add their own.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the row was inserted, set by the database.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * Row id, and the only handle GET/PUT/DELETE /channels/types/{id} accept. Not the type `code`. No example is published because no id this app could invent names a row a tenant holds.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The type a channel created without one gets. Exactly one row carries it.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Seeded on install rather than added by the merchant. A flag about origin only — a system type is still renameable, reorderable and retirable.
     */
    @SerializedName("is_system")
    var is_system: Boolean?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Sort position. GET /channels/types always answers in this order and takes no `order` parameter. It is not unique and defaults to 0, so ties are broken by `code` — the order is total, which is what makes paging the list safe to walk.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The tenant that owns this row. Added by the data plane, not by this app: it is not a column of schema.json, so it is read-only and `?tenant_id=` is not a filter — the key is silently dropped and never reaches the `filter` echo.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * The fallback name. `labels` carries the per-locale ones. Rows seeded before 0.7.0 hold a serialized locale map here instead (PE-452).
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Semantic badge colour for this type, for a client that renders the list. The client owns what each tone looks like; the value only says what it MEANS.
     */
    @SerializedName("tone")
    var tone: ChannelTypeTone?,

    /**
     * When the row was last written, set by the database.
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
        ) = ChannelTypeRow(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            description = map["description"] as? Any,
            descriptions = map["descriptions"] as? Any,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            is_system = map["is_system"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            tenant_id = map["tenant_id"] as? String,
            title = map["title"] as? Any,
            tone = ChannelTypeTone.values().find { it.value == (map["tone"] as? String) } ?: null,
            updated_at = map["updated_at"] as? String,
        )
    }
}