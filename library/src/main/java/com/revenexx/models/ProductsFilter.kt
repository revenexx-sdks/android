package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `products` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class ProductsFilter<T>(
    /**
     * The literal `?attribute_values=` value this call was understood to carry.
     */
    @SerializedName("attribute_values")
    var attribute_values: String?,

    /**
     * The literal `?completeness=` value this call was understood to carry.
     */
    @SerializedName("completeness")
    var completeness: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?deleted_at=` value this call was understood to carry.
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * The literal `?enabled=` value this call was understood to carry.
     */
    @SerializedName("enabled")
    var enabled: String?,

    /**
     * The literal `?family_id=` value this call was understood to carry.
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * The literal `?family_variant_id=` value this call was understood to carry.
     */
    @SerializedName("family_variant_id")
    var family_variant_id: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?kind=` value this call was understood to carry.
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * The literal `?label=` value this call was understood to carry.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The literal `?parent_id=` value this call was understood to carry.
     */
    @SerializedName("parent_id")
    var parent_id: String?,

    /**
     * The literal `?quantified_associations=` value this call was understood to carry.
     */
    @SerializedName("quantified_associations")
    var quantified_associations: String?,

    /**
     * The literal `?sku=` value this call was understood to carry.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The literal `?tax_class=` value this call was understood to carry.
     */
    @SerializedName("tax_class")
    var tax_class: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_values" to attribute_values as Any,
        "completeness" to completeness as Any,
        "created_at" to created_at as Any,
        "deleted_at" to deleted_at as Any,
        "enabled" to enabled as Any,
        "family_id" to family_id as Any,
        "family_variant_id" to family_variant_id as Any,
        "id" to id as Any,
        "kind" to kind as Any,
        "label" to label as Any,
        "parent_id" to parent_id as Any,
        "quantified_associations" to quantified_associations as Any,
        "sku" to sku as Any,
        "tax_class" to tax_class as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            attribute_values: String?,
            completeness: String?,
            created_at: String?,
            deleted_at: String?,
            enabled: String?,
            family_id: String?,
            family_variant_id: String?,
            id: String?,
            kind: String?,
            label: String?,
            parent_id: String?,
            quantified_associations: String?,
            sku: String?,
            tax_class: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = ProductsFilter<Map<String, Any>>(
            attribute_values,
            completeness,
            created_at,
            deleted_at,
            enabled,
            family_id,
            family_variant_id,
            id,
            kind,
            label,
            parent_id,
            quantified_associations,
            sku,
            tax_class,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = ProductsFilter<T>(
            attribute_values = map["attribute_values"] as? String,
            completeness = map["completeness"] as? String,
            created_at = map["created_at"] as? String,
            deleted_at = map["deleted_at"] as? String,
            enabled = map["enabled"] as? String,
            family_id = map["family_id"] as? String,
            family_variant_id = map["family_variant_id"] as? String,
            id = map["id"] as? String,
            kind = map["kind"] as? String,
            label = map["label"] as? String,
            parent_id = map["parent_id"] as? String,
            quantified_associations = map["quantified_associations"] as? String,
            sku = map["sku"] as? String,
            tax_class = map["tax_class"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}