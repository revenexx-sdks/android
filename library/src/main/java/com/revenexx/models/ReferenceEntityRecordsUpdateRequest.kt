package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class ReferenceEntityRecordsUpdateRequest(
    /**
     * Every attribute value the record carries, in ONE jsonb document — the core of an attribute-driven PIM. A record's properties are not columns here: they are rows in `attributes`, selected per family by `family_attributes`, and their values live under their attribute CODE inside this object.
     * 
     * Four buckets, and an attribute's own flags decide which one it writes to:
     * 
     *   `common`                    the attribute is neither localizable nor scopable — one value, full stop.
     *                               `{"common": {"net_weight": 2.4, "colour": "black"}}`
     *   `locale_specific`           `localizable`: one value per language tag.
     *                               `{"locale_specific": {"de_DE": {"name": "Akku-Bohrschrauber"}}}`
     *   `channel_specific`          `scopable`: one value per channel.
     *                               `{"channel_specific": {"b2b": {"minimum_order_quantity": 6}}}`
     *   `channel_locale_specific`   both: one value per channel AND language tag.
     *                               `{"channel_locale_specific": {"b2b": {"de_DE": {"description": "…"}}}}`
     * 
     * A reader takes the most specific bucket that carries the code and falls back through locale, then channel, then `common`. `common` is always last and always consulted, because early imports wrote everything there whatever an attribute's flags said — a reader that skipped it reports an imported catalog as empty. `GET /products/attribute-schema` answers, per field, the exact path a value belongs at (`storage.path`) and that full fallback order (`from`), so no client has to re-derive any of this.
     * 
     * The value itself is whatever the attribute's `type` implies: a string, a number, a boolean, an option CODE for a select (never its label), a list of codes for a multi-select, `{"amount": …, "unit": …}` for a measure, a list of `{"amount": …, "currency": …}` for a price, an asset code for media.
     * 
     * Defaults to `{}`, and an empty object is a normal state — a record nobody has enriched yet. The declared type also admits an array only because every jsonb column of this app shares one mapping; an array is not meaningful here and every reader in this app treats a non-object as empty.
     * 
     * Which attributes a record of this entity has comes from `attributes` rows with `entity_type: "reference_entity"` and `entity_ref` equal to the entity's code — `GET /products/attribute-schema?entity_type=reference_entity&entity_ref=brand` answers it in one call.
     */
    @SerializedName("attribute_values")
    var attribute_values: Any?,

    /**
     * The record's stable identifier — the value a product stores when it points at this record, the same way a select stores an option code. Unique within the entity.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * What the record is called, per language tag — the text a picker shows while the code is what gets written.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Which reference entity this record belongs to.
     */
    @SerializedName("reference_entity_id")
    var reference_entity_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_values" to attribute_values as Any,
        "code" to code as Any,
        "labels" to labels as Any,
        "reference_entity_id" to reference_entity_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReferenceEntityRecordsUpdateRequest(
            attribute_values = map["attribute_values"] as? Any,
            code = map["code"] as? String,
            labels = map["labels"] as? Any,
            reference_entity_id = map["reference_entity_id"] as? String,
        )
    }
}