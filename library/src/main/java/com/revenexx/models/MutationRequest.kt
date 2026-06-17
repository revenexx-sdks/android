package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class MutationRequest(
    /**
     * 
     */
    @SerializedName("langcode")
    var langcode: String?,

    /**
     * 
     */
    @SerializedName("payload")
    var payload: Any?,

    /**
     * Mutation plugin id (add, move, delete, duplicate, update_field_value, ...).
     */
    @SerializedName("plugin")
    val plugin: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "langcode" to langcode as Any,
        "payload" to payload as Any,
        "plugin" to plugin as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MutationRequest(
            langcode = map["langcode"] as? String,
            payload = map["payload"] as? Any,
            plugin = map["plugin"] as String,
        )
    }
}