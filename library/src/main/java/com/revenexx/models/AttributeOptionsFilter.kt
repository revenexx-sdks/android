package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `attribute_options` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class AttributeOptionsFilter<T>(
    /**
     * The literal `?attribute_id=` value this call was understood to carry.
     */
    @SerializedName("attribute_id")
    var attribute_id: String?,

    /**
     * The literal `?code=` value this call was understood to carry.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?labels=` value this call was understood to carry.
     */
    @SerializedName("labels")
    var labels: String?,

    /**
     * The literal `?position=` value this call was understood to carry.
     */
    @SerializedName("position")
    var position: String?,

    /**
     * The literal `?swatch=` value this call was understood to carry.
     */
    @SerializedName("swatch")
    var swatch: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_id" to attribute_id as Any,
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "swatch" to swatch as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            attribute_id: String?,
            code: String?,
            created_at: String?,
            id: String?,
            labels: String?,
            position: String?,
            swatch: String?,
            data: Map<String, Any>
        ) = AttributeOptionsFilter<Map<String, Any>>(
            attribute_id,
            code,
            created_at,
            id,
            labels,
            position,
            swatch,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AttributeOptionsFilter<T>(
            attribute_id = map["attribute_id"] as? String,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            labels = map["labels"] as? String,
            position = map["position"] as? String,
            swatch = map["swatch"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}