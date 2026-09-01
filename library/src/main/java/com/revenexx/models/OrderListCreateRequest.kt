package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderListCreateRequest(
    /**
     * Optional initial positions. Every one is validated — and article-checked where `reject_unknown_articles` is on — BEFORE the list row is written, so a rejected position never leaves an empty list behind.
     */
    @SerializedName("items")
    var items: List<OrderListItemInput>?,

    /**
     * List kind — the `code` of one of the tenant's own kinds (GET /orderlists/kinds); defaults to the flagged one, or the market's 'default_kind' setting.
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * Free-form data the tenant keeps on the list — an ERP requisition number, a department, whatever an integration needs to recognise the list again. Never read by this app, and never merged: a write replaces the whole document.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What the buyer calls this list. Free text, at least one character, and not unique: two contacts may both keep a "Weekly office supplies". It is also the name a NEW cart gets when POST /orderlists/{id}/cart creates one.
     */
    @SerializedName("name")
    val name: String,

    /**
     * The organization the sharing is scoped to. Null means the list can only ever be the owner's own: `shared` is meaningless without it, because there is no set of people to share with. It is also what the order conversion hands the orders app as the buying organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * The contact who owns the list. Ownership IS the authorization here: a caller the gateway resolved to a contact sees their own lists plus their organization's shared ones, and may write only their own — unless `shared_lists_editable` opens a shared list to the whole owning organization. Set once at create; no route moves a list to another owner.
     */
    @SerializedName("owner_id")
    val owner_id: String,

    /**
     * The owner's display name as it stood when the list was created — a snapshot, so renaming the contact does not rewrite it. Carried so a shared list can say whose it is without a call to the contacts app.
     */
    @SerializedName("owner_name")
    val owner_name: String,

    /**
     * Whether the OWNING ORGANIZATION may see this list. False — the default — keeps it private to `owner_id`, and a foreign private list answers 404 rather than 403, so an outsider learns nothing from the difference. True lets every contact of `organization_id` READ it, and write it only where the tenant turned on the `shared_lists_editable` setting. A list with no `organization_id` shares with nobody however this is set.
     */
    @SerializedName("shared")
    var shared: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
        "kind" to kind as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "organization_id" to organization_id as Any,
        "owner_id" to owner_id as Any,
        "owner_name" to owner_name as Any,
        "shared" to shared as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListCreateRequest(
            items = (map["items"] as List<Map<String, Any>>).map { OrderListItemInput.from(map = it) },
            kind = map["kind"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as String,
            organization_id = map["organization_id"] as? String,
            owner_id = map["owner_id"] as String,
            owner_name = map["owner_name"] as String,
            shared = map["shared"] as? Boolean,
        )
    }
}