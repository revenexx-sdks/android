package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Suppression(
    /**
     * 
     */
    @SerializedName("address")
    val address: String,

    /**
     * 
     */
    @SerializedName("address_hash")
    val address_hash: String,

    /**
     * 
     */
    @SerializedName("channel")
    val channel: String,

    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("expires_at")
    val expires_at: String,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("note")
    val note: String,

    /**
     * 
     */
    @SerializedName("reason")
    val reason: String,

    /**
     * 
     */
    @SerializedName("scope")
    val scope: String,

    /**
     * 
     */
    @SerializedName("source")
    val source: String,

    /**
     * 
     */
    @SerializedName("tenant_id")
    val tenant_id: String,

    /**
     * 
     */
    @SerializedName("updated_at")
    val updated_at: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "address" to address as Any,
        "address_hash" to address_hash as Any,
        "channel" to channel as Any,
        "created_at" to created_at as Any,
        "expires_at" to expires_at as Any,
        "id" to id as Any,
        "note" to note as Any,
        "reason" to reason as Any,
        "scope" to scope as Any,
        "source" to source as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Suppression(
            address = map["address"] as String,
            address_hash = map["address_hash"] as String,
            channel = map["channel"] as String,
            created_at = map["created_at"] as String,
            expires_at = map["expires_at"] as String,
            id = map["id"] as String,
            note = map["note"] as String,
            reason = map["reason"] as String,
            scope = map["scope"] as String,
            source = map["source"] as String,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
        )
    }
}