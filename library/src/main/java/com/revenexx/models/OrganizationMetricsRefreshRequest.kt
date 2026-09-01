package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrganizationMetricsRefreshRequest(
    /**
     * Anchor for the rolling windows — pass back the value the previous call returned.
     */
    @SerializedName("as_of")
    var as_of: String?,

    /**
     * Continue an unfinished refresh: the value the previous call returned, verbatim. It is the id of the last organization processed, so only a value this API handed out ever resolves.
     */
    @SerializedName("cursor")
    var cursor: String?,

    /**
     * Refresh exactly these organizations in one call instead of walking all of them.
     */
    @SerializedName("organization_ids")
    var organization_ids: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "as_of" to as_of as Any,
        "cursor" to cursor as Any,
        "organization_ids" to organization_ids as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrganizationMetricsRefreshRequest(
            as_of = map["as_of"] as? String,
            cursor = map["cursor"] as? String,
            organization_ids = map["organization_ids"] as? List<String>,
        )
    }
}