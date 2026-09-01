package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ProductTaxRef(
    /**
     * The product's id.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The product's resolved display name, or its SKU when the catalog holds no name for it.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The SKU, so a caller that asked by id can key its own answer by SKU and the other way round.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The tax class key the prices app resolves a rate from. Null means the product names none and the caller has to fall back to its own default.
     */
    @SerializedName("tax_class")
    var tax_class: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "label" to label as Any,
        "sku" to sku as Any,
        "tax_class" to tax_class as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductTaxRef(
            id = map["id"] as? String,
            label = map["label"] as? String,
            sku = map["sku"] as? String,
            tax_class = map["tax_class"] as? String,
        )
    }
}