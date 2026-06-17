package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AssetsCreateRequest(
    /**
     * 
     */
    @SerializedName("asset_family_id")
    val asset_family_id: String,

    /**
     * 
     */
    @SerializedName("attribute_values")
    var attribute_values: Any?,

    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("media_uuid")
    var media_uuid: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "asset_family_id" to asset_family_id as Any,
        "attribute_values" to attribute_values as Any,
        "code" to code as Any,
        "media_uuid" to media_uuid as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AssetsCreateRequest(
            asset_family_id = map["asset_family_id"] as String,
            attribute_values = map["attribute_values"] as? Any,
            code = map["code"] as String,
            media_uuid = map["media_uuid"] as? String,
        )
    }
}