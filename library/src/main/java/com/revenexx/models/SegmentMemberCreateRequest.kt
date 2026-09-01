package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentMemberSource

/**
 * Add one organization to a segment. Use source='manual' (the default) for hand-picked members; rule members are materialized by the recompute route.
 */
data class SegmentMemberCreateRequest(
    /**
     * The member company. Segments group companies, never people — a person is reached through their organization.
     */
    @SerializedName("organization_id")
    val organization_id: String,

    /**
     * The segment.
     */
    @SerializedName("segment_id")
    val segment_id: String,

    /**
     * How this membership came about: 'manual' is hand-picked, 'rule' was materialized by a recompute. The distinction is load-bearing — a recompute only ever inserts and deletes 'rule' rows, so a hand-picked member survives every rule change. Default 'manual'.
     */
    @SerializedName("source")
    var source: SegmentMemberSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "organization_id" to organization_id as Any,
        "segment_id" to segment_id as Any,
        "source" to source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentMemberCreateRequest(
            organization_id = map["organization_id"] as String,
            segment_id = map["segment_id"] as String,
            source = SegmentMemberSource.values().find { it.value == (map["source"] as? String) } ?: null,
        )
    }
}