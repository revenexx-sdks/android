package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The preferences to store for the calling user.
 */
data class PageUserSettingsRequest(
    /**
     * The whole preferences bag — replaced, not merged, so send all of it. Its keys vary by the editor build and this app reads none of them. Null or omitted stores `{}`, which is how a user resets their editor.
     */
    @SerializedName("settings")
    var settings: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "settings" to settings as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageUserSettingsRequest(
            settings = map["settings"] as? Any,
        )
    }
}