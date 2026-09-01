package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributeGroupsCreateRequest(
    /**
     * The group's stable identifier, and the value an `AttributeField` carries as its `group` — a SECTION of the product form, not a label. Unique per tenant and the key an import joins on.
     */
    @SerializedName("code")
    val code: String,

    /**
     * The section heading a person sees, keyed by language tag. The code is never shown to an operator; a tag nobody translated falls back to the next filled one, then to English.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Where this section sits in a form, ascending. Sections that tie keep the order the database returns them in.
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "labels" to labels as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeGroupsCreateRequest(
            code = map["code"] as String,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}