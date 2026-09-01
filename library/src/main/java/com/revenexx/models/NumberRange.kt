package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A counter that issues human-readable numbers, one per series: orders, delivery notes, returns. The format is {prefix}{counter padded to padding}{suffix}, and drawing a number moves the counter.
 */
data class NumberRange(
    /**
     * The sales channel this range was created for, as a label. It does NOT select the range: a draw finds the range by `code` alone, and the unique index (tenant, code) means one code is one range per tenant — so an order on another channel draws from the same range this one names. Null on the three seeded ranges, which is every tenant-wide range.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Which counter this is, in the app's own words: 'order' numbers orders, 'delivery' numbers delivery notes, 'return' numbers returns. Unique per tenant, and the value the order_number_range_code / delivery_number_range_code / return_number_range_code settings point at — a setting naming a code no range carries is the 422 'number_range_missing'.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The last number DRAWN — state, not configuration. The next draw is counter + step and writes the new value back, so moving this forward skips numbers and moving it back re-issues them (and the unique index then answers 409).
     */
    @SerializedName("counter")
    var counter: Long?,

    /**
     * When the range was created.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the number range.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Free-form data for the caller. This app stores it and returns it, and reads nothing out of it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * How wide the counter is written, zero-padded: 6 makes 123 into 000123. 0 writes the bare number. Widening it later does not renumber what was already drawn.
     */
    @SerializedName("padding")
    var padding: Long?,

    /**
     * The gap between the position numbers of a new order: 10 numbers the lines 10, 20, 30 — room to slot a line in between later without renumbering the rest. Read from the ORDER range only.
     */
    @SerializedName("position_step")
    var position_step: Long?,

    /**
     * Literal text in front of the counter: 'ORD-' turns counter 123 into ORD-000123. Empty by default.
     */
    @SerializedName("prefix")
    var prefix: String?,

    /**
     * How far the counter moves per draw. 1 is consecutive numbering; a larger step is what a merchant chooses who does not want their order volume readable off an invoice.
     */
    @SerializedName("step")
    var step: Long?,

    /**
     * Literal text after the counter — a market or year marker on merchants who number that way. Empty by default, which is what most of them use.
     */
    @SerializedName("suffix")
    var suffix: String?,

    /**
     * When the range last changed — which includes every single number draw, because a draw writes the counter.
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