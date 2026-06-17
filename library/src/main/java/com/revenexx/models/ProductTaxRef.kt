package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ProductTaxRef(
    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("tax_class")
    var tax_class: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "sku" to sku as Any,
        "tax_class" to tax_class as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductTaxRef(
            id = map["id"] as? String,
            sku = map["sku"] as? String,
            tax_class = map["tax_class"] as? String,
        )
    }
}