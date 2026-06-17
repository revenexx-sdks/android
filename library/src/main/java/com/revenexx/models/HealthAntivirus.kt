package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.HealthAntivirusStatus

/**
 * Health Antivirus
 */
data class HealthAntivirus(
    /**
     * Antivirus status. Possible values are: `disabled`, `offline`, `online`
     */
    @SerializedName("status")
    val status: HealthAntivirusStatus,

    /**
     * Antivirus version.
     */
    @SerializedName("version")
    val version: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "status" to status.value as Any,
        "version" to version as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = HealthAntivirus(
            status = HealthAntivirusStatus.values().find { it.value == map["status"] as String }!!,
            version = map["version"] as String,
        )
    }
}