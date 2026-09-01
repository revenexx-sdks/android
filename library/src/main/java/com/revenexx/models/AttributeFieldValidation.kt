package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The limits the value has to satisfy, ready to hand to a form validator. Only the seven keys below are republished; anything else the tenant stored in `attributes.validation` stays there.
 */
data class AttributeFieldValidation(
    /**
     * Largest permitted number.
     */
    @SerializedName("max")
    var max: Double?,

    /**
     * Most entries.
     */
    @SerializedName("max_items")
    var max_items: Long?,

    /**
     * Longest permitted text.
     */
    @SerializedName("max_length")
    var max_length: Long?,

    /**
     * Smallest permitted number, for a number or measure field.
     */
    @SerializedName("min")
    var min: Double?,

    /**
     * Fewest entries, for a multi-select or a collection.
     */
    @SerializedName("min_items")
    var min_items: Long?,

    /**
     * Shortest permitted text.
     */
    @SerializedName("min_length")
    var min_length: Long?,

    /**
     * A regular expression the text has to match.
     */
    @SerializedName("pattern")
    var pattern: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "max" to max as Any,
        "max_items" to max_items as Any,
        "max_length" to max_length as Any,
        "min" to min as Any,
        "min_items" to min_items as Any,
        "min_length" to min_length as Any,
        "pattern" to pattern as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeFieldValidation(
            max = (map["max"] as? Number)?.toDouble(),
            max_items = (map["max_items"] as? Number)?.toLong(),
            max_length = (map["max_length"] as? Number)?.toLong(),
            min = (map["min"] as? Number)?.toDouble(),
            min_items = (map["min_items"] as? Number)?.toLong(),
            min_length = (map["min_length"] as? Number)?.toLong(),
            pattern = map["pattern"] as? String,
        )
    }
}