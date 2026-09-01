package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call applied, echoed back. Every value is the raw query string, never the column's own type: `?is_default=true` comes back as `"true"`. A `?column=value` naming a column this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list, and the key missing from here is the only way to find out.
 */
data class MarketFilter(
    /**
     * The `code` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The `created_at` filter as it arrived, verbatim. Present only when the call sent it. Any form the database accepts as a timestamp, including a bare date.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The `currency` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The `id` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The `is_default` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("is_default")
    var is_default: String?,

    /**
     * The `labels` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("labels")
    var labels: String?,

    /**
     * The `name` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The `position` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("position")
    var position: String?,

    /**
     * The `status` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("status")
    var status: String?,

    /**
     * The `updated_at` filter as it arrived, verbatim. Present only when the call sent it. Any form the database accepts as a timestamp, including a bare date.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketFilter(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? String,
            labels = map["labels"] as? String,
            name = map["name"] as? String,
            position = map["position"] as? String,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}