package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FamiliesCreateRequest(
    /**
     * The family's stable identifier — which set of attributes a product of this family HAS. Unique per tenant, and the value `GET /products/attribute-schema?family_code=` resolves.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Which attribute code carries the product's main image — the one a grid thumbnail and a picker read.
     */
    @SerializedName("image_attribute")
    var image_attribute: String?,

    /**
     * Which attribute CODE carries the display name of a product in this family. A product's name is an attribute, not a column, and which attribute it is, is per family. Null falls back to the `default_label_attribute` setting and then to the conventional `name`.
     */
    @SerializedName("label_attribute")
    var label_attribute: String?,

    /**
     * What the family is called, per language tag — the name an operator picks from, while the code is what everything else joins on.
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "image_attribute" to image_attribute as Any,
        "label_attribute" to label_attribute as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamiliesCreateRequest(
            code = map["code"] as String,
            image_attribute = map["image_attribute"] as? String,
            label_attribute = map["label_attribute"] as? String,
            labels = map["labels"] as? Any,
        )
    }
}