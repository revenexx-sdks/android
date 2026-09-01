package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Everything about a form that is not a field: what the storefront renders around the inputs, what happens after a successful submit, and who is told about it. Open jsonb, so an unknown key is stored and handed back rather than refused — the keys below are the ones something actually READS, and each says which reader that is. Null on a form nobody has configured, which is not an error: every one of these has a fallback.
 */
data class FormSettings<T>(
    /**
     * What the storefront runs after a successful submit, in order. Executed by the cover BFF, not by this API — this app only stores them, and a workflow that wants the same event should listen to `form.submitted` instead.
     */
    @SerializedName("actions")
    var actions: List<FormPostSubmitAction<T>>?,

    /**
     * The language the definition itself is written in. Read by the storefront BFF, which overlays `i18n` on top of it.
     */
    @SerializedName("default_locale")
    var default_locale: String?,

    /**
     * Translations for the definition, keyed by language tag and then by field name: `{"en": {"email": {"label": "Email"}}}`. Only `label`, `placeholder` and `help` are overlaid — a translation of anything else is stored and ignored. Applied by the storefront BFF before the definition reaches the browser, so the API always returns the untranslated definition.
     */
    @SerializedName("i18n")
    var i18n: Any?,

    /**
     * This form's own notification recipient, read by THIS app at insert. It beats the tenant's `notify_email` setting; null means fall back to the tenant. The storefront never sees it — the BFF hands the browser only the submit label and the success message.
     */
    @SerializedName("notify_email")
    var notify_email: String?,

    /**
     * The submit button caption, read by the storefront. Null falls back to 'Submit'.
     */
    @SerializedName("submit_label")
    var submit_label: String?,

    /**
     * What the visitor reads after a successful submit, read by the storefront. Null falls back to a generic thank-you.
     */
    @SerializedName("success_message")
    var success_message: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "actions" to actions?.map { it.toMap() } as Any,
        "default_locale" to default_locale as Any,
        "i18n" to i18n as Any,
        "notify_email" to notify_email as Any,
        "submit_label" to submit_label as Any,
        "success_message" to success_message as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            actions: List<FormPostSubmitAction<Map<String, Any>>>?,
            default_locale: String?,
            i18n: Any?,
            notify_email: String?,
            submit_label: String?,
            success_message: String?,
            data: Map<String, Any>
        ) = FormSettings<Map<String, Any>>(
            actions,
            default_locale,
            i18n,
            notify_email,
            submit_label,
            success_message,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FormSettings<T>(
            actions = (map["actions"] as List<Map<String, Any>>).map { FormPostSubmitAction.from(map = it, nestedType) },
            default_locale = map["default_locale"] as? String,
            i18n = map["i18n"] as? Any,
            notify_email = map["notify_email"] as? String,
            submit_label = map["submit_label"] as? String,
            success_message = map["success_message"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}