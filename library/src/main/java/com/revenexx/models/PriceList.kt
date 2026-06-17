package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PriceList(
    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("contact_id")
    var contact_id: String?,

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
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("market_id")
    var market_id: String?,

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
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * 
     */
    @SerializedName("priority")
    var priority: Long?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("tax_included")
    var tax_included: Boolean?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * 
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "code" to code as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "market_id" to market_id as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "organization_id" to organization_id as Any,
        "priority" to priority as Any,
        "status" to status as Any,
        "tax_included" to tax_included as Any,
        "updated_at" to updated_at as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceList(
            channel_id = map["channel_id"] as? String,
            code = map["code"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            market_id = map["market_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            organization_id = map["organization_id"] as? String,
            priority = (map["priority"] as? Number)?.toLong(),
            status = map["status"] as? String,
            tax_included = map["tax_included"] as? Boolean,
            updated_at = map["updated_at"] as? String,
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}