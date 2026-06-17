package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrganizationStatus

/**
 * Partial update — omitted fields keep their current value; external_team_id is mirror-managed and ignored.
 */
data class OrganizationUpdateRequest(
    /**
     * Company name — mirrored to the platform team.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Free-form organization settings.
     */
    @SerializedName("settings")
    var settings: Any?,

    /**
     * Default 'active'.
     */
    @SerializedName("status")
    var status: OrganizationStatus?,

    /**
     * 
     */
    @SerializedName("vat_id")
    var vat_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name as Any,
        "settings" to settings as Any,
        "status" to status?.value as Any,
        "vat_id" to vat_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrganizationUpdateRequest(
            name = map["name"] as? String,
            settings = map["settings"] as? Any,
            status = OrganizationStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            vat_id = map["vat_id"] as? String,
        )
    }
}