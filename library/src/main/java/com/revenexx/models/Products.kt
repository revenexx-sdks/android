package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Products(
    /**
     * 
     */
    @SerializedName("attribute_values")
    var attribute_values: Any?,

    /**
     * 
     */
    @SerializedName("completeness")
    var completeness: Any?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * 
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * 
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * 
     */
    @SerializedName("family_variant_id")
    var family_variant_id: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * 
     */
    @SerializedName("parent_id")
    var parent_id: String?,

    /**
     * 
     */
    @SerializedName("quantified_associations")
    var quantified_associations: Any?,

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

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

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
        "parent_id" to parent_id as Any,
        "quantified_associations" to quantified_associations as Any,
        "sku" to sku as Any,
        "tax_class" to tax_class as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Products(
            attribute_values = map["attribute_values"] as? Any,
            completeness = map["completeness"] as? Any,
            created_at = map["created_at"] as? String,
            deleted_at = map["deleted_at"] as? String,
            enabled = map["enabled"] as? Boolean,
            family_id = map["family_id"] as? String,
            family_variant_id = map["family_variant_id"] as? String,
            id = map["id"] as? String,
            kind = map["kind"] as? String,
            parent_id = map["parent_id"] as? String,
            quantified_associations = map["quantified_associations"] as? Any,
            sku = map["sku"] as? String,
            tax_class = map["tax_class"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}