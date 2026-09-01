package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ShippingRateTier(
    /**
     * When the row was created (UTC).
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Lower bound of this tier, in the method's matrix measure — kilograms (or whatever the market's `weight_unit` names, converted through its factor) for a weight matrix, items for quantity, money in the method's currency for order_value, and the raw attribute value for 'attribute'. INCLUSIVE: the tier applies from this value upward, and the tier that wins is the one with the highest from_value at or below the measured value, so a measure of exactly 10 is priced by the tier at 10 rather than the one below it. The last tier has no upper bound. Unique per method — a second tier at the same threshold is a 409, because which of the two won would be whatever the database returned first.
     */
    @SerializedName("from_value")
    var from_value: Double?,

    /**
     * Row id, assigned by the database on insert.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The shipping method this tier prices. Set from the path on every write, so a body that names another method is ignored rather than obeyed. ON DELETE CASCADE: deleting the method deletes its table.
     */
    @SerializedName("method_id")
    var method_id: String?,

    /**
     * Display order in the matrix editor (default 0; a bulk replace derives it from the array index). Pricing reads from_value, never this.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * What this tier costs, in the method's currency. Charged in full for the whole consignment — a matrix is a lookup table, not a rate per unit.
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * When the row was last written (UTC).
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "from_value" to from_value as Any,
        "id" to id as Any,
        "method_id" to method_id as Any,
        "position" to position as Any,
        "price" to price as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRateTier(
            created_at = map["created_at"] as? String,
            from_value = (map["from_value"] as? Number)?.toDouble(),
            id = map["id"] as? String,
            method_id = map["method_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
        )
    }
}