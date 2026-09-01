package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentMemberSource

/**
 * One organization inside one segment, and the record of how it got there (hand-picked or matched by the rule).
 */
data class SegmentMember(
    /**
     * When the organization joined the segment.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the membership row.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The member company. Segments group companies, never people — a person is reached through their organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * The segment.
     */
    @SerializedName("segment_id")
    var segment_id: String?,

    /**
     * How this membership came about: 'manual' is hand-picked, 'rule' was materialized by a recompute. The distinction is load-bearing — a recompute only ever inserts and deletes 'rule' rows, so a hand-picked member survives every rule change.
     */
    @SerializedName("source")
    var source: SegmentMemberSource?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "organization_id" to organization_id as Any,
        "segment_id" to segment_id as Any,
        "source" to source?.value as Any,
        "tenant_id" to tenant_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentMember(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            organization_id = map["organization_id"] as? String,
            segment_id = map["segment_id"] as? String,
            source = SegmentMemberSource.values().find { it.value == (map["source"] as? String) } ?: null,
            tenant_id = map["tenant_id"] as? String,
        )
    }
}