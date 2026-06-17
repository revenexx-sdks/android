package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Template Variable
 */
data class TemplateVariable(
    /**
     * Variable Description.
     */
    @SerializedName("description")
    val description: String,

    /**
     * Variable Name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Variable Placeholder.
     */
    @SerializedName("placeholder")
    val placeholder: String,

    /**
     * Is the variable required?
     */
    @SerializedName("required")
    val required: Boolean,

    /**
     * Variable secret flag. Secret variables can only be updated or deleted, but never read.
     */
    @SerializedName("secret")
    val secret: Boolean,

    /**
     * Variable Type.
     */
    @SerializedName("type")
    val type: String,

    /**
     * Variable Value.
     */
    @SerializedName("value")
    val value: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "name" to name as Any,
        "placeholder" to placeholder as Any,
        "required" to required as Any,
        "secret" to secret as Any,
        "type" to type as Any,
        "value" to value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TemplateVariable(
            description = map["description"] as String,
            name = map["name"] as String,
            placeholder = map["placeholder"] as String,
            required = map["required"] as Boolean,
            secret = map["secret"] as Boolean,
            type = map["type"] as String,
            value = map["value"] as String,
        )
    }
}