package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class AssetFamiliesUpdateRequest(
    /**
     * The asset family's stable identifier — a class of media with one shared shape. Unique per tenant.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * What the asset family is called, per language tag.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * How a file of this family is named, so an import can bind a file to a product without a mapping table. `source` is the product value the file name is built from, `pattern` how it is assembled, `allowed_extensions` what may be uploaded.
     */
    @SerializedName("naming_convention")
    var naming_convention: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "labels" to labels as Any,
        "naming_convention" to naming_convention as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AssetFamiliesUpdateRequest(
            code = map["code"] as? String,
            labels = map["labels"] as? Any,
            naming_convention = map["naming_convention"] as? Any,
        )
    }
}