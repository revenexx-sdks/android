package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributeFieldOption(
    /**
     * What to show in the picker, already resolved for the requested locale.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Colour/texture chip, when the option carries one — `{"hex": "#c0c0c0"}`.
     */
    @SerializedName("swatch")
    var swatch: Any?,

    /**
     * The stored value — an `attribute_options.code`, or a `reference_entity_records.code` when the options ARE a reference entity. This, never the label, is what goes into `attribute_values`.
     */
    @SerializedName("value")
    var value: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "label" to label as Any,
        "swatch" to swatch as Any,
        "value" to value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeFieldOption(
            label = map["label"] as? String,
            swatch = map["swatch"] as? Any,
            value = map["value"] as? String,
        )
    }
}