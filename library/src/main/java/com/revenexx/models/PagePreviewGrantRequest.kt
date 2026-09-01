package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * How long the link should live.
 */
data class PagePreviewGrantRequest(
    /**
     * Hours until the link expires. Defaults to 72. After that `GET /pages/delivery/preview/{token}` answers 410 rather than 404, so the holder can tell "expired" from "wrong link".
     */
    @SerializedName("ttlHours")
    var ttlHours: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "ttlHours" to ttlHours as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PagePreviewGrantRequest(
            ttlHours = (map["ttlHours"] as? Number)?.toLong(),
        )
    }
}