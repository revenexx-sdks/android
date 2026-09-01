package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Everything the blökkli editor runs on, for one page in one language, materialized at the current point of the undo history. The theme adapter maps it 1:1 onto blökkli's MappedState.
 */
data class EditorState(
    /**
     * Whether the caller may write. False means every write answers 409 until `POST …/take-ownership` — so the editor should go read-only rather than let someone type into a refusal.
     */
    @SerializedName("currentUserIsOwner")
    var currentUserIsOwner: Boolean?,

    /**
     * Every entity-reference field of every block — the fields an editor drags a product or a media item into.
     */
    @SerializedName("droppableFieldValues")
    var droppableFieldValues: List<Any>?,

    /**
     * The open working copy, or `null` when nobody has started editing — in which case the state shown is simply the published one.
     */
    @SerializedName("editState")
    var editState: Any?,

    /**
     * What the tenant's settings allow, so a client hides a control instead of discovering the refusal.
     */
    @SerializedName("features")
    var features: Any?,

    /**
     * The block tree, flattened into one entry per (host, field) pair. This is the list the editor renders and drops into.
     */
    @SerializedName("fields")
    var fields: List<Any>?,

    /**
     * Analyze findings that were dismissed for this page, so the editor stops reporting them.
     */
    @SerializedName("ignoredAnalyzeIdentifiers")
    var ignoredAnalyzeIdentifiers: List<String>?,

    /**
     * The language this whole state was resolved for — the `?langcode` that was applied, or the page's source language.
     */
    @SerializedName("langcode")
    var langcode: String?,

    /**
     * The page-level field values the edit state changed, merged source-then-language — `{ "title": …, "slug": …, "meta": … }`. Empty when nobody edited the page itself, only its blocks.
     */
    @SerializedName("mutatedEntity")
    var mutatedEntity: Any?,

    /**
     * The PAGE-level display options after the unpublished changes, as a flat `option key → value` map. Theme-defined.
     */
    @SerializedName("mutatedHostOptions")
    var mutatedHostOptions: Any?,

    /**
     * Every block's display options after the unpublished changes, keyed by block uuid: `{ "<uuid>": { "background": "grey" } }`.
     */
    @SerializedName("mutatedOptions")
    var mutatedOptions: Any?,

    /**
     * The undo/redo history, oldest first. Its length and `editState.currentIndex` are what an undo button and a history sidebar are drawn from.
     */
    @SerializedName("mutations")
    var mutations: List<Any>?,

    /**
     * The page itself, with the unpublished edits already applied — so the title here is what publishing would store, not what is stored now.
     */
    @SerializedName("page")
    var page: Any?,

    /**
     * Every string field of every block, flattened. It is what the translation view and the CSV export are built on — one row per translatable string.
     */
    @SerializedName("textFieldValues")
    var textFieldValues: List<Any>?,

    /**
     * Every language this page exists in, so the editor can offer a language switcher that shows what is missing.
     */
    @SerializedName("translations")
    var translations: List<Any>?,

    /**
     * Why publishing would be refused right now. Empty means `POST …/publish` succeeds without `force`.
     */
    @SerializedName("violations")
    var violations: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currentUserIsOwner" to currentUserIsOwner as Any,
        "droppableFieldValues" to droppableFieldValues as Any,
        "editState" to editState as Any,
        "features" to features as Any,
        "fields" to fields as Any,
        "ignoredAnalyzeIdentifiers" to ignoredAnalyzeIdentifiers as Any,
        "langcode" to langcode as Any,
        "mutatedEntity" to mutatedEntity as Any,
        "mutatedHostOptions" to mutatedHostOptions as Any,
        "mutatedOptions" to mutatedOptions as Any,
        "mutations" to mutations as Any,
        "page" to page as Any,
        "textFieldValues" to textFieldValues as Any,
        "translations" to translations as Any,
        "violations" to violations as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = EditorState(
            currentUserIsOwner = map["currentUserIsOwner"] as? Boolean,
            droppableFieldValues = map["droppableFieldValues"] as? List<Any>,
            editState = map["editState"] as? Any,
            features = map["features"] as? Any,
            fields = map["fields"] as? List<Any>,
            ignoredAnalyzeIdentifiers = map["ignoredAnalyzeIdentifiers"] as? List<String>,
            langcode = map["langcode"] as? String,
            mutatedEntity = map["mutatedEntity"] as? Any,
            mutatedHostOptions = map["mutatedHostOptions"] as? Any,
            mutatedOptions = map["mutatedOptions"] as? Any,
            mutations = map["mutations"] as? List<Any>,
            page = map["page"] as? Any,
            textFieldValues = map["textFieldValues"] as? List<Any>,
            translations = map["translations"] as? List<Any>,
            violations = map["violations"] as? List<Any>,
        )
    }
}