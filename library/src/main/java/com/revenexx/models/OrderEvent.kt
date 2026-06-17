package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderEvent(
    /**
     * 
     */
    @SerializedName("actor")
    var actor: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * 
     */
    @SerializedName("payload")
    var payload: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "actor" to actor as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "name" to name as Any,
        "order_id" to order_id as Any,
        "payload" to payload as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderEvent(
            actor = map["actor"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            name = map["name"] as? String,
            order_id = map["order_id"] as? String,
            payload = map["payload"] as? Any,
        )
    }
}