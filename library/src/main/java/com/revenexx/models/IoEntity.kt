package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One importable / exportable entity of an installed app.
 */
data class IoEntity(
    /**
     * 
     */
    @SerializedName("app")
    var app: String?,

    /**
     * 
     */
    @SerializedName("entity")
    var entity: String?,

    /**
     * Humanised entity name for pickers.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The physical table name Baseline provisioned.
     */
    @SerializedName("table")
    var table: String?,

    /**
     * 
     */
    @SerializedName("vendor")
    var vendor: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "entity" to entity as Any,
        "label" to label as Any,
        "table" to table as Any,
        "vendor" to vendor as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IoEntity(
            app = map["app"] as? String,
            entity = map["entity"] as? String,
            label = map["label"] as? String,
            table = map["table"] as? String,
            vendor = map["vendor"] as? String,
        )
    }
}