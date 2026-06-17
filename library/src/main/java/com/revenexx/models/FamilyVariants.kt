package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FamilyVariants(
    /**
     * 
     */
    @SerializedName("axes")
    var axes: Any?,

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
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "axes" to axes as Any,
        "code" to code as Any,
        "created_at" to created_at as Any,
        "family_id" to family_id as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamilyVariants(
            axes = map["axes"] as? Any,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            family_id = map["family_id"] as? String,
            id = map["id"] as? String,
            labels = map["labels"] as? Any,
            updated_at = map["updated_at"] as? String,
        )
    }
}