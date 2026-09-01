package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What was measured and stored into `products.completeness` by this call — how much of what the family requires the product actually carries.
 */
data class ProductCompleteness(
    /**
     * When this measurement was taken. It is a snapshot: editing the product does not update it, the next `POST /products/{id}/completeness` does.
     */
    @SerializedName("computed_at")
    var computed_at: String?,

    /**
     * How many of those carry a value — in ANY bucket, so a name held only in German counts.
     */
    @SerializedName("filled")
    var filled: Long?,

    /**
     * Attribute codes with no value in any bucket.
     */
    @SerializedName("missing")
    var missing: List<String>?,

    /**
     * filled / required, 0..1. A family that requires nothing is 1, not undefined.
     */
    @SerializedName("ratio")
    var ratio: Double?,

    /**
     * Attributes the product's family marks is_required.
     */
    @SerializedName("required")
    var required: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "computed_at" to computed_at as Any,
        "filled" to filled as Any,
        "missing" to missing as Any,
        "ratio" to ratio as Any,
        "required" to required as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductCompleteness(
            computed_at = map["computed_at"] as? String,
            filled = (map["filled"] as? Number)?.toLong(),
            missing = map["missing"] as? List<String>,
            ratio = (map["ratio"] as? Number)?.toDouble(),
            required = (map["required"] as? Number)?.toLong(),
        )
    }
}