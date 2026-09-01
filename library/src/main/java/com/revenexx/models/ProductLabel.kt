package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ProductLabelAttributeSource
import com.revenexx.enums.ProductLabelSource

/**
 * 
 */
data class ProductLabel(
    /**
     * The attribute code the name was read from.
     */
    @SerializedName("attribute")
    var attribute: String?,

    /**
     * How that attribute was chosen: 'family' is the product's own `families.label_attribute`, 'setting' the tenant's `default_label_attribute`, 'convention' the built-in fallback to `name` when neither says anything.
     */
    @SerializedName("attribute_from")
    var attribute_from: ProductLabelAttributeSource?,

    /**
     * The product's id.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The name to show. Never empty — read `source` before treating it as a name, because `sku` there means this is the SKU standing in for one.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Which locale the value came out of, when it came from a locale bucket. Null for a value in `common` and for the SKU fallback.
     */
    @SerializedName("locale")
    var locale: String?,

    /**
     * The SKU, which is also the fallback shown as `label` when the catalog holds no name.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Which bucket of attribute_values the name came from. 'sku' means the catalog holds no name for this product — show that as a missing name, not as a name.
     */
    @SerializedName("source")
    var source: ProductLabelSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute" to attribute as Any,
        "attribute_from" to attribute_from?.value as Any,
        "id" to id as Any,
        "label" to label as Any,
        "locale" to locale as Any,
        "sku" to sku as Any,
        "source" to source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductLabel(
            attribute = map["attribute"] as? String,
            attribute_from = ProductLabelAttributeSource.values().find { it.value == (map["attribute_from"] as? String) } ?: null,
            id = map["id"] as? String,
            label = map["label"] as? String,
            locale = map["locale"] as? String,
            sku = map["sku"] as? String,
            source = ProductLabelSource.values().find { it.value == (map["source"] as? String) } ?: null,
        )
    }
}