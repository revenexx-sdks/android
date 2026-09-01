package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributeOptionsCreateRequest(
    /**
     * The select / multi-select attribute these are the permitted values of. Deleting the attribute deletes its options with it.
     */
    @SerializedName("attribute_id")
    val attribute_id: String,

    /**
     * The value actually STORED in a record's `attribute_values` when this option is picked — never the label. Unique within the attribute.
     */
    @SerializedName("code")
    val code: String,

    /**
     * What the option is called, per language tag. Two tenants may label the same code differently; only the code is ever written into a record.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Order in the dropdown, ascending. Options that tie keep the order the database returns them in, so give every option a position if the order matters.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * A colour or texture chip for the picker. Null for an option that is not visual.
     */
    @SerializedName("swatch")
    var swatch: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_id" to attribute_id as Any,
        "code" to code as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "swatch" to swatch as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeOptionsCreateRequest(
            attribute_id = map["attribute_id"] as String,
            code = map["code"] as String,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            swatch = map["swatch"] as? Any,
        )
    }
}