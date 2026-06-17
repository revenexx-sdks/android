package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class EligiblePaymentMethod(
    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

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
    @SerializedName("fee")
    var fee: Double?,

    /**
     * 
     */
    @SerializedName("fee_type")
    var fee_type: String?,

    /**
     * 
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

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
    @SerializedName("provider")
    var provider: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "fee" to fee as Any,
        "fee_type" to fee_type as Any,
        "kind" to kind as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "provider" to provider as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = EligiblePaymentMethod(
            code = map["code"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            fee = (map["fee"] as? Number)?.toDouble(),
            fee_type = map["fee_type"] as? String,
            kind = map["kind"] as? String,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            provider = map["provider"] as? String,
        )
    }
}