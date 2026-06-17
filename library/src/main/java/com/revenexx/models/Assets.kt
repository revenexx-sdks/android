package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Assets(
    /**
     * 
     */
    @SerializedName("asset_family_id")
    var asset_family_id: String?,

    /**
     * 
     */
    @SerializedName("attribute_values")
    var attribute_values: Any?,

    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("media_uuid")
    var media_uuid: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "asset_family_id" to asset_family_id as Any,
        "attribute_values" to attribute_values as Any,
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "media_uuid" to media_uuid as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Assets(
            asset_family_id = map["asset_family_id"] as? String,
            attribute_values = map["attribute_values"] as? Any,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            media_uuid = map["media_uuid"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}