package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ProductLabelSource

/**
 * 
 */
data class ProductGridRow(
    /**
     * The grid cells: one key per attribute code that `columns` lists with `source: "attribute"`, holding the value already resolved out of `attribute_values` for the requested context. A code the product carries no value for is null rather than absent, so a row is the same shape whatever it holds. The keys are the tenant's own attribute codes, which is why this object has no fixed properties — read `columns` for the set.
     */
    @SerializedName("attributes")
    var attributes: Any?,

    /**
     * The stored `products.completeness` document, verbatim. Null means it has never been computed — not that the product is empty.
     */
    @SerializedName("completeness")
    var completeness: Any?,

    /**
     * Whether the product is offered.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * That family's code, resolved here so a grid can show and group by it without a second read.
     */
    @SerializedName("family_code")
    var family_code: String?,

    /**
     * The product's family. Null is the state that makes completeness impossible.
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * The product's id — what a row click navigates with.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 'simple', 'model' or 'variant' — a model is a row a person should not price or sell.
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * The resolved display name. Never empty; read `label_source` before showing it as a name.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Which attribute code the name was read from, per this product's family.
     */
    @SerializedName("label_attribute")
    var label_attribute: String?,

    /**
     * Which bucket of attribute_values the name came from. 'sku' means the catalog holds no name for this product — show that as a missing name, not as a name.
     */
    @SerializedName("label_source")
    var label_source: ProductLabelSource?,

    /**
     * The merchant's article number.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * When the product row was last written — the column a "recently changed" sort uses.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attributes" to attributes as Any,
        "completeness" to completeness as Any,
        "enabled" to enabled as Any,
        "family_code" to family_code as Any,
        "family_id" to family_id as Any,
        "id" to id as Any,
        "kind" to kind as Any,
        "label" to label as Any,
        "label_attribute" to label_attribute as Any,
        "label_source" to label_source?.value as Any,
        "sku" to sku as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductGridRow(
            attributes = map["attributes"] as? Any,
            completeness = map["completeness"] as? Any,
            enabled = map["enabled"] as? Boolean,
            family_code = map["family_code"] as? String,
            family_id = map["family_id"] as? String,
            id = map["id"] as? String,
            kind = map["kind"] as? String,
            label = map["label"] as? String,
            label_attribute = map["label_attribute"] as? String,
            label_source = ProductLabelSource.values().find { it.value == (map["label_source"] as? String) } ?: null,
            sku = map["sku"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}