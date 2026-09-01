package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One post-submit action. `webhook` POSTs `{form, source, data}` to `url`; `entity` writes the mapped fields into another app's entity; `event` is a no-op, because `form.submitted` already carries it.
 */
data class FormPostSubmitAction<T>(
    /**
     * Entity actions: the app that owns the target entity, e.g. 'crm'.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Disabled actions are skipped. An action with no flag is not run.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Entity actions: the entity to write, e.g. 'contacts'.
     */
    @SerializedName("entity")
    var entity: String?,

    /**
     * Entity actions: which submitted value becomes which column — `{"source": "email", "target": "email"}` reads `data.email` and writes it to the target's `email`.
     */
    @SerializedName("mapping")
    var mapping: List<FormActionMapping>?,

    /**
     * Webhook actions: the HTTP method. Defaults to POST.
     */
    @SerializedName("method")
    var method: String?,

    /**
     * Entity actions: an explicit route to POST to, instead of the one built from `app` and `entity`.
     */
    @SerializedName("xpath")
    var xpath: String?,

    /**
     * Which action this is: 'webhook', 'entity' or 'event'.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * Webhook actions: where to POST. It is called with an 8 second timeout and its answer is not shown to the visitor.
     */
    @SerializedName("url")
    var url: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "enabled" to enabled as Any,
        "entity" to entity as Any,
        "mapping" to mapping?.map { it.toMap() } as Any,
        "method" to method as Any,
        "path" to xpath as Any,
        "type" to type as Any,
        "url" to url as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            app: String?,
            enabled: Boolean?,
            entity: String?,
            mapping: List<FormActionMapping>?,
            method: String?,
            xpath: String?,
            type: String?,
            url: String?,
            data: Map<String, Any>
        ) = FormPostSubmitAction<Map<String, Any>>(
            app,
            enabled,
            entity,
            mapping,
            method,
            xpath,
            type,
            url,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FormPostSubmitAction<T>(
            app = map["app"] as? String,
            enabled = map["enabled"] as? Boolean,
            entity = map["entity"] as? String,
            mapping = (map["mapping"] as List<Map<String, Any>>).map { FormActionMapping.from(map = it) },
            method = map["method"] as? String,
            xpath = map["path"] as? String,
            type = map["type"] as? String,
            url = map["url"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}