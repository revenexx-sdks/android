package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `families` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class FamiliesFilter<T>(
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
     * The literal `?image_attribute=` value this call was understood to carry.
     */
    @SerializedName("image_attribute")
    var image_attribute: String?,

    /**
     * The literal `?label_attribute=` value this call was understood to carry.
     */
    @SerializedName("label_attribute")
    var label_attribute: String?,

    /**
     * The literal `?labels=` value this call was understood to carry.
     */
    @SerializedName("labels")
    var labels: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "image_attribute" to image_attribute as Any,
        "label_attribute" to label_attribute as Any,
        "labels" to labels as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            code: String?,
            created_at: String?,
            id: String?,
            image_attribute: String?,
            label_attribute: String?,
            labels: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = FamiliesFilter<Map<String, Any>>(
            code,
            created_at,
            id,
            image_attribute,
            label_attribute,
            labels,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FamiliesFilter<T>(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            image_attribute = map["image_attribute"] as? String,
            label_attribute = map["label_attribute"] as? String,
            labels = map["labels"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}