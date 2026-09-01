package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One change to the page.
 */
data class MutationRequest(
    /**
     * Which language the returned state should be resolved for. Not the language the change is written in — that lives in the payload.
     */
    @SerializedName("langcode")
    var langcode: String?,

    /**
     * The arguments of that change; the keys depend on the plugin (`add` takes `{ bundle, hostEntityType, hostEntityUuid, hostField }`, `move` takes `{ uuid, preceedingUuid }`, and so on). Anything non-deterministic in it — new uuids, a library item's tree, a copied subtree — is resolved once here and stored, so replaying the log is deterministic forever.
     */
    @SerializedName("payload")
    var payload: Any?,

    /**
     * Which kind of change this is — `add`, `move`, `delete`, `duplicate`, `update_field_value`, `update_options`, … An id this app does not implement is refused with 400 rather than stored, because the log has to replay.
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