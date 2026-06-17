package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The blökkli adapter state: page, translations, edit state + mutation log, materialized field lists, mutated options/entity values, text field values, droppable field values and violations.
 */
data class EditorState(
    /**
     * 
     */
    @SerializedName("currentUserIsOwner")
    var currentUserIsOwner: Boolean?,

    /**
     * 
     */
    @SerializedName("droppableFieldValues")
    var droppableFieldValues: List<Any>?,

    /**
     * 
     */
    @SerializedName("editState")
    var editState: Any?,

    /**
     * 
     */
    @SerializedName("fields")
    var fields: List<Any>?,

    /**
     * 
     */
    @SerializedName("ignoredAnalyzeIdentifiers")
    var ignoredAnalyzeIdentifiers: List<String>?,

    /**
     * 
     */
    @SerializedName("langcode")
    var langcode: String?,

    /**
     * 
     */
    @SerializedName("mutatedEntity")
    var mutatedEntity: Any?,

    /**
     * 
     */
    @SerializedName("mutatedHostOptions")
    var mutatedHostOptions: Any?,

    /**
     * 
     */
    @SerializedName("mutatedOptions")
    var mutatedOptions: Any?,

    /**
     * 
     */
    @SerializedName("mutations")
    var mutations: List<Any>?,

    /**
     * 
     */
    @SerializedName("page")
    var page: Any?,

    /**
     * 
     */
    @SerializedName("textFieldValues")
    var textFieldValues: List<Any>?,

    /**
     * 
     */
    @SerializedName("translations")
    var translations: List<Any>?,

    /**
     * 
     */
    @SerializedName("violations")
    var violations: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currentUserIsOwner" to currentUserIsOwner as Any,
        "droppableFieldValues" to droppableFieldValues as Any,
        "editState" to editState as Any,
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