package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class ProductAssociationsUpdateRequest(
    /**
     * 
     */
    @SerializedName("association_type_id")
    var association_type_id: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * 
     */
    @SerializedName("target_product_id")
    var target_product_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "association_type_id" to association_type_id as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "target_product_id" to target_product_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductAssociationsUpdateRequest(
            association_type_id = map["association_type_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            target_product_id = map["target_product_id"] as? String,
        )
    }
}