package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class AssociationTypesUpdateRequest(
    /**
     * The kind of relation between two products. Unique per tenant.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Declares that a relation of this kind carries a quantity — a bundle, a bill of materials. `product_associations.quantity` is where that number goes, and it is meaningless without this flag.
     */
    @SerializedName("is_quantified")
    var is_quantified: Boolean?,

    /**
     * Declares the relation symmetric — an accessory of A is an accessory of B. It is a declaration a client reads: this app stores one row per direction and does not create the mirror for you.
     */
    @SerializedName("is_two_way")
    var is_two_way: Boolean?,

    /**
     * What the relation is called in a product form, per language tag.
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_quantified" to is_quantified as Any,
        "is_two_way" to is_two_way as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AssociationTypesUpdateRequest(
            code = map["code"] as? String,
            is_quantified = map["is_quantified"] as? Boolean,
            is_two_way = map["is_two_way"] as? Boolean,
            labels = map["labels"] as? Any,
        )
    }
}