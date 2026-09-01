package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — rename, visibility or kind. Positions go through the items routes, and the owner cannot be changed.
 */
data class OrderListUpdateRequest(
    /**
     * List kind — the `code` of one of the tenant's own kinds (GET /orderlists/kinds); defaults to the flagged one, or the market's 'default_kind' setting.
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * Free-form data the tenant keeps on the list — an ERP requisition number, a department, whatever an integration needs to recognise the list again. Never read by this app, and never merged: a write replaces the whole document.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What the buyer calls this list. Free text, at least one character, and not unique: two contacts may both keep a "Weekly office supplies". It is also the name a NEW cart gets when POST /orderlists/{id}/cart creates one.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Whether the OWNING ORGANIZATION may see this list. False — the default — keeps it private to `owner_id`, and a foreign private list answers 404 rather than 403, so an outsider learns nothing from the difference. True lets every contact of `organization_id` READ it, and write it only where the tenant turned on the `shared_lists_editable` setting. A list with no `organization_id` shares with nobody however this is set.
     */
    @SerializedName("shared")
    var shared: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "kind" to kind as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "shared" to shared as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListUpdateRequest(
            kind = map["kind"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            shared = map["shared"] as? Boolean,
        )
    }
}