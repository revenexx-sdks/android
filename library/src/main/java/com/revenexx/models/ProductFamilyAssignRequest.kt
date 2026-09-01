package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Name the family either way — `family_id` wins when both are sent. The family has to exist already; this route assigns one, it does not create one.
 */
data class ProductFamilyAssignRequest(
    /**
     * Alternative to family_id — a `families.code` this tenant holds, from `GET /products/families`. No example: a code is tenant data, and any value published here names a family somebody does not have.
     */
    @SerializedName("family_code")
    var family_code: String?,

    /**
     * The family to assign.
     */
    @SerializedName("family_id")
    var family_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "family_code" to family_code as Any,
        "family_id" to family_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductFamilyAssignRequest(
            family_code = map["family_code"] as? String,
            family_id = map["family_id"] as? String,
        )
    }
}