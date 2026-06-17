package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Number pattern: '{prefix}{counter padded to padding}{suffix}'.
 */
data class OrderNumberRangeCreateRequest(
    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Range key drawn by the app ('order', 'delivery', 'return') — unique per tenant.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Current counter value (default 0) — the next number draws counter+step.
     */
    @SerializedName("counter")
    var counter: Long?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Zero-padding width of the counter (default 6).
     */
    @SerializedName("padding")
    var padding: Long?,

    /**
     * Position numbering increment for order items (default 10).
     */
    @SerializedName("position_step")
    var position_step: Long?,

    /**
     * Default ''.
     */
    @SerializedName("prefix")
    var prefix: String?,

    /**
     * Counter increment per drawn number (default 1).
     */
    @SerializedName("step")
    var step: Long?,

    /**
     * Default ''.
     */
    @SerializedName("suffix")
    var suffix: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "code" to code as Any,
        "counter" to counter as Any,
        "metadata" to metadata as Any,
        "padding" to padding as Any,
        "position_step" to position_step as Any,
        "prefix" to prefix as Any,
        "step" to step as Any,
        "suffix" to suffix as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderNumberRangeCreateRequest(
            channel_id = map["channel_id"] as? String,
            code = map["code"] as String,
            counter = (map["counter"] as? Number)?.toLong(),
            metadata = map["metadata"] as? Any,
            padding = (map["padding"] as? Number)?.toLong(),
            position_step = (map["position_step"] as? Number)?.toLong(),
            prefix = map["prefix"] as? String,
            step = (map["step"] as? Number)?.toLong(),
            suffix = map["suffix"] as? String,
        )
    }
}