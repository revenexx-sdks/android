package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AssetsSource

/**
 * An asset is bound to its bytes on creation: source 'storage' needs `storage_asset_id`, source 'external' needs `external_url` AND an explicit `source` — the column defaults to 'storage', so an `external_url` on its own is refused by the database with a bare "a value is not allowed here".
 */
data class AssetsCreateRequest(
    /**
     * The asset family this asset belongs to — which attributes it carries and how its file is named. A create falls back to the `default_asset_family` tenant setting when the body names none.
     */
    @SerializedName("asset_family_id")
    val asset_family_id: String,

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
     * Which attributes an asset of this family has comes from `attributes` rows with `entity_type: "asset"` and `entity_ref` equal to the family's code — alt text, copyright, an expiry date.
     */
    @SerializedName("attribute_values")
    var attribute_values: Any?,

    /**
     * The asset's stable identifier within its family — the value a product's media attribute stores. Unique per family.
     */
    @SerializedName("code")
    val code: String,

    /**
     * The path the CDN serves this asset under — the convenient value for rendering. It changes when the file is moved, so never join on it.
     */
    @SerializedName("delivery_path")
    var delivery_path: String?,

    /**
     * Absolute URL of an externally hosted file. Required when `source` is `external`, and accepted only when the tenant has `allow_external_media` on and the host is on its `external_media_allowed_hosts` list — `POST /products/assets` is the only place an external URL can enter the catalog, so it is the only place those are enforced.
     */
    @SerializedName("external_url")
    var external_url: String?,

    /**
     * Where the bytes live: 'storage' is this platform's object store and needs `storage_asset_id`, 'external' is somebody else's host and needs `external_url`. The database enforces the pair, so neither half can be stored on its own.
     */
    @SerializedName("source")
    var source: AssetsSource?,

    /**
     * The stable `ast_…` id of the storage object. It survives a rename or a folder move, which is exactly why it and not the delivery path is the identifier. Required when `source` is `storage`.
     */
    @SerializedName("storage_asset_id")
    var storage_asset_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "asset_family_id" to asset_family_id as Any,
        "attribute_values" to attribute_values as Any,
        "code" to code as Any,
        "delivery_path" to delivery_path as Any,
        "external_url" to external_url as Any,
        "source" to source?.value as Any,
        "storage_asset_id" to storage_asset_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AssetsCreateRequest(
            asset_family_id = map["asset_family_id"] as String,
            attribute_values = map["attribute_values"] as? Any,
            code = map["code"] as String,
            delivery_path = map["delivery_path"] as? String,
            external_url = map["external_url"] as? String,
            source = AssetsSource.values().find { it.value == (map["source"] as? String) } ?: null,
            storage_asset_id = map["storage_asset_id"] as? String,
        )
    }
}