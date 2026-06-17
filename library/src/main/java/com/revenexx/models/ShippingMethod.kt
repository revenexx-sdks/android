package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ShippingMethod(
    /**
     * 
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("countries")
    var countries: Any?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * 
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * 
     */
    @SerializedName("eta_days_max")
    var eta_days_max: Long?,

    /**
     * 
     */
    @SerializedName("eta_days_min")
    var eta_days_min: Long?,

    /**
     * 
     */
    @SerializedName("free_above")
    var free_above: Double?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("matrix_attribute")
    var matrix_attribute: String?,

    /**
     * 
     */
    @SerializedName("matrix_basis")
    var matrix_basis: String?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * 
     */
    @SerializedName("pricing_type")
    var pricing_type: String?,

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
        "carrier" to carrier as Any,
        "code" to code as Any,
        "countries" to countries as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "enabled" to enabled as Any,
        "eta_days_max" to eta_days_max as Any,
        "eta_days_min" to eta_days_min as Any,
        "free_above" to free_above as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "matrix_attribute" to matrix_attribute as Any,
        "matrix_basis" to matrix_basis as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "price" to price as Any,
        "pricing_type" to pricing_type as Any,
        "tax_class" to tax_class as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingMethod(
            carrier = map["carrier"] as? String,
            code = map["code"] as? String,
            countries = map["countries"] as? Any,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            enabled = map["enabled"] as? Boolean,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            free_above = (map["free_above"] as? Number)?.toDouble(),
            id = map["id"] as? String,
            labels = map["labels"] as? Any,
            matrix_attribute = map["matrix_attribute"] as? String,
            matrix_basis = map["matrix_basis"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            pricing_type = map["pricing_type"] as? String,
            tax_class = map["tax_class"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}