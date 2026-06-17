package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Organization(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("external_team_id")
    var external_team_id: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("settings")
    var settings: Any?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("vat_id")
    var vat_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "external_team_id" to external_team_id as Any,
        "id" to id as Any,
        "name" to name as Any,
        "settings" to settings as Any,
        "status" to status as Any,
        "updated_at" to updated_at as Any,
        "vat_id" to vat_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Organization(
            created_at = map["created_at"] as? String,
            external_team_id = map["external_team_id"] as? String,
            id = map["id"] as? String,
            name = map["name"] as? String,
            settings = map["settings"] as? Any,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
            vat_id = map["vat_id"] as? String,
        )
    }
}