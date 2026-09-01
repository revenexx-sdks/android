package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReferenceEntitiesCreateRequest(
    /**
     * The entity's stable identifier — a domain of records the catalog POINTS AT instead of duplicating, so a brand is edited once and not on nine thousand products. Unique per tenant.
     */
    @SerializedName("code")
    val code: String,

    /**
     * A delivery path or URL for the entity's own icon. Cosmetic — nothing in this app resolves it.
     */
    @SerializedName("image")
    var image: String?,

    /**
     * What the entity is called, per language tag — the heading over its record list.
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "image" to image as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReferenceEntitiesCreateRequest(
            code = map["code"] as String,
            image = map["image"] as? String,
            labels = map["labels"] as? Any,
        )
    }
}