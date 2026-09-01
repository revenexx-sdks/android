package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One publication of this page, without the snapshot — who published, when, and under what name.
 */
data class PageRevisionRef(
    /**
     * When this revision was published.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The user id that published.
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * That user's display name, copied in at publish time so the history stays readable after the user is gone.
     */
    @SerializedName("created_by_name")
    var created_by_name: String?,

    /**
     * The revision id. A page's `published_revision_id` points at one of these, and it is the only thing delivery reads.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * What this publication was called, e.g. "Autumn campaign". It is what turns the history into a list of changes rather than a list of timestamps.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The page this revision belongs to.
     */
    @SerializedName("page_id")
    var page_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "created_by_name" to created_by_name as Any,
        "id" to id as Any,
        "label" to label as Any,
        "page_id" to page_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageRevisionRef(
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            created_by_name = map["created_by_name"] as? String,
            id = map["id"] as? String,
            label = map["label"] as? String,
            page_id = map["page_id"] as? String,
        )
    }
}