package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One renderable field. A superset of the manifest's `Field`: the three additions (`localized`, `channel_scoped`, `storage`) carry what a static manifest never has to say, because a manifest's fields are columns and these are keys inside one.
 */
data class AttributeField(
    /**
     * One value per channel rather than one value.
     */
    @SerializedName("channel_scoped")
    var channel_scoped: Boolean?,

    /**
     * Dotted read paths, most specific first — the documented precedence (channel+locale → locale → channel → common). `common` is always last and always present, because early imports wrote there whatever the attribute's flags say.
     */
    @SerializedName("from")
    var from: List<String>?,

    /**
     * Attribute-group code — the section this field belongs in.
     */
    @SerializedName("group")
    var group: String?,

    /**
     * That section's heading, resolved for the requested locale — so a form can be built without reading `attribute_groups` as well.
     */
    @SerializedName("group_label")
    var group_label: String?,

    /**
     * Resolved for the requested locale, falling back to English, then to the code.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * One value per locale rather than one value.
     */
    @SerializedName("localized")
    var localized: Boolean?,

    /**
     * The attribute code — the key the value is stored under.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Present on select / multi-select. Two sources, one shape: rows of `attribute_options` for an enumeration the attribute owns, or the records of a reference entity for an attribute that points at one. Empty is an answer: the list has no members yet.
     */
    @SerializedName("options")
    var options: List<AttributeFieldOption>?,

    /**
     * The family's ordering of this attribute, falling back to the attribute's own.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The field must not be edited in this context. Today the one cause is a variant axis on a product model; `readonly_reason` says which.
     */
    @SerializedName("readonly")
    var readonly: Boolean?,

    /**
     * Why the field is locked — a variant axis on a product model is set on its variants.
     */
    @SerializedName("readonly_reason")
    var readonly_reason: String?,

    /**
     * Present when the options ARE a reference entity's records: the code of that entity, so a client can offer to manage the values rather than only pick from them.
     */
    @SerializedName("reference_entity")
    var reference_entity: String?,

    /**
     * The family's `is_required`, narrowed to the requested channel when `required_channels` names any.
     */
    @SerializedName("required")
    var required: Boolean?,

    /**
     * Where the value lives. Absent on an app whose custom fields are plain columns — then the field name IS the column.
     */
    @SerializedName("storage")
    var storage: AttributeFieldStorage?,

    /**
     * The control to draw. Mapped from `attributes.type`, which carries no CHECK on purpose — an unknown type answers 'text' rather than nothing.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * The attribute's `is_unique` — the value is meant to identify the product. Advisory: no index enforces it, so a client that cares has to check.
     */
    @SerializedName("unique")
    var unique: Boolean?,

    /**
     * Offered units of a `measure` field, from the attribute's `config.units`.
     */
    @SerializedName("units")
    var units: List<String>?,

    /**
     * The limits the value has to satisfy, ready to hand to a form validator. Only the seven keys below are republished; anything else the tenant stored in `attributes.validation` stays there.
     */
    @SerializedName("validation")
    var validation: AttributeFieldValidation?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_scoped" to channel_scoped as Any,
        "from" to from as Any,
        "group" to group as Any,
        "group_label" to group_label as Any,
        "label" to label as Any,
        "localized" to localized as Any,
        "name" to name as Any,
        "options" to options?.map { it.toMap() } as Any,
        "position" to position as Any,
        "readonly" to readonly as Any,
        "readonly_reason" to readonly_reason as Any,
        "reference_entity" to reference_entity as Any,
        "required" to required as Any,
        "storage" to storage?.toMap() as Any,
        "type" to type as Any,
        "unique" to unique as Any,
        "units" to units as Any,
        "validation" to validation?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeField(
            channel_scoped = map["channel_scoped"] as? Boolean,
            from = map["from"] as? List<String>,
            group = map["group"] as? String,
            group_label = map["group_label"] as? String,
            label = map["label"] as? String,
            localized = map["localized"] as? Boolean,
            name = map["name"] as? String,
            options = (map["options"] as List<Map<String, Any>>).map { AttributeFieldOption.from(map = it) },
            position = (map["position"] as? Number)?.toLong(),
            readonly = map["readonly"] as? Boolean,
            readonly_reason = map["readonly_reason"] as? String,
            reference_entity = map["reference_entity"] as? String,
            required = map["required"] as? Boolean,
            storage = AttributeFieldStorage.from(map = map["storage"] as Map<String, Any>),
            type = map["type"] as? String,
            unique = map["unique"] as? Boolean,
            units = map["units"] as? List<String>,
            validation = AttributeFieldValidation.from(map = map["validation"] as Map<String, Any>),
        )
    }
}