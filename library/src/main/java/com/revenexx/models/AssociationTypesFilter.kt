package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `association_types` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class AssociationTypesFilter<T>(
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
     * The literal `?is_quantified=` value this call was understood to carry.
     */
    @SerializedName("is_quantified")
    var is_quantified: String?,

    /**
     * The literal `?is_two_way=` value this call was understood to carry.
     */
    @SerializedName("is_two_way")
    var is_two_way: String?,

    /**
     * The literal `?labels=` value this call was understood to carry.
     */
    @SerializedName("labels")
    var labels: String?,

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
        "is_quantified" to is_quantified as Any,
        "is_two_way" to is_two_way as Any,
        "labels" to labels as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            code: String?,
            created_at: String?,
            id: String?,
            is_quantified: String?,
            is_two_way: String?,
            labels: String?,
            data: Map<String, Any>
        ) = AssociationTypesFilter<Map<String, Any>>(
            code,
            created_at,
            id,
            is_quantified,
            is_two_way,
            labels,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AssociationTypesFilter<T>(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_quantified = map["is_quantified"] as? String,
            is_two_way = map["is_two_way"] as? String,
            labels = map["labels"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}