package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class NumberRange(
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
    @SerializedName("counter")
    var counter: Long?,

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
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("padding")
    var padding: Long?,

    /**
     * 
     */
    @SerializedName("position_step")
    var position_step: Long?,

    /**
     * 
     */
    @SerializedName("prefix")
    var prefix: String?,

    /**
     * 
     */
    @SerializedName("step")
    var step: Long?,

    /**
     * 
     */
    @SerializedName("suffix")
    var suffix: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "code" to code as Any,
        "counter" to counter as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "metadata" to metadata as Any,
        "padding" to padding as Any,
        "position_step" to position_step as Any,
        "prefix" to prefix as Any,
        "step" to step as Any,
        "suffix" to suffix as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = NumberRange(
            channel_id = map["channel_id"] as? String,
            code = map["code"] as? String,
            counter = (map["counter"] as? Number)?.toLong(),
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            metadata = map["metadata"] as? Any,
            padding = (map["padding"] as? Number)?.toLong(),
            position_step = (map["position_step"] as? Number)?.toLong(),
            prefix = map["prefix"] as? String,
            step = (map["step"] as? Number)?.toLong(),
            suffix = map["suffix"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}