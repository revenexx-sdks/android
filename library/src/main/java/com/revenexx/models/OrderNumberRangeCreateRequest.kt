package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Number pattern: '{prefix}{counter padded to padding}{suffix}'.
 */
data class OrderNumberRangeCreateRequest(
    /**
     * The sales channel this range was created for, as a label. It does NOT select the range: a draw finds the range by `code` alone, and the unique index (tenant, code) means one code is one range per tenant — so an order on another channel draws from the same range this one names. Null on the three seeded ranges, which is every tenant-wide range.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Which counter this is, in the app's own words: 'order' numbers orders, 'delivery' numbers delivery notes, 'return' numbers returns. Unique per tenant, and the value the order_number_range_code / delivery_number_range_code / return_number_range_code settings point at — a setting naming a code no range carries is the 422 'number_range_missing'.
     */
    @SerializedName("code")
    val code: String,

    /**
     * The last number DRAWN — state, not configuration. The next draw is counter + step and writes the new value back, so moving this forward skips numbers and moving it back re-issues them (and the unique index then answers 409). Defaults to 0, so the first number drawn is step.
     */
    @SerializedName("counter")
    var counter: Long?,

    /**
     * Free-form data for the caller. This app stores it and returns it, and reads nothing out of it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * How wide the counter is written, zero-padded: 6 makes 123 into 000123. 0 writes the bare number. Widening it later does not renumber what was already drawn. Defaults to 6.
     */
    @SerializedName("padding")
    var padding: Long?,

    /**
     * The gap between the position numbers of a new order: 10 numbers the lines 10, 20, 30 — room to slot a line in between later without renumbering the rest. Read from the ORDER range only. Defaults to 10.
     */
    @SerializedName("position_step")
    var position_step: Long?,

    /**
     * Literal text in front of the counter: 'ORD-' turns counter 123 into ORD-000123. Empty by default. Defaults to ''.
     */
    @SerializedName("prefix")
    var prefix: String?,

    /**
     * How far the counter moves per draw. 1 is consecutive numbering; a larger step is what a merchant chooses who does not want their order volume readable off an invoice. Defaults to 1.
     */
    @SerializedName("step")
    var step: Long?,

    /**
     * Literal text after the counter — a market or year marker on merchants who number that way. Empty by default, which is what most of them use. Defaults to ''.
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