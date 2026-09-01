package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The family the fields belong to, or null when none was named — then the answer is every attribute of the `entity_type`, which is what a reference entity or an asset family has instead of a family.
 */
data class AttributeSchemaFamily(
    /**
     * The family's code — the value `?family_code=` takes.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The family's id.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The family name, resolved for the requested locale.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Which of these fields is the product's display name.
     */
    @SerializedName("label_attribute")
    var label_attribute: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "id" to id as Any,
        "label" to label as Any,
        "label_attribute" to label_attribute as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeSchemaFamily(
            code = map["code"] as? String,
            id = map["id"] as? String,
            label = map["label"] as? String,
            label_attribute = map["label_attribute"] as? String,
        )
    }
}