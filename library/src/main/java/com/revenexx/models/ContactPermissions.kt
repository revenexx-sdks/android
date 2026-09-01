package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A contact's effective grants, derived from its role on every read — nothing here is stored, so a role change can never leave a stale grant behind. Carried here so a BFF does not need a second call to decide what to render.
 */
data class ContactPermissions(
    /**
     * False while the contact is blocked or its registration is still pending/rejected — it holds the role but must not act on it.
     */
    @SerializedName("active")
    var active: Boolean?,

    /**
     * The person these grants belong to. Null when the answer describes nobody — a user with no contact mirrored against it.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * Amount ceiling in the market's currency; null means no ceiling. Only meaningful together with the 'orders.approve' permission.
     */
    @SerializedName("order_approval_limit")
    var order_approval_limit: Double?,

    /**
     * The organization the role applies inside. Null for a standalone (B2C) contact — a role with no company to hold it in.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * What this role may do. Derived from the role — see GET /customers/roles.
     */
    @SerializedName("permissions")
    var permissions: List<String>?,

    /**
     * The role this contact holds in its organization, and the only input to `permissions`.
     */
    @SerializedName("role")
    var role: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "active" to active as Any,
        "contact_id" to contact_id as Any,
        "order_approval_limit" to order_approval_limit as Any,
        "organization_id" to organization_id as Any,
        "permissions" to permissions as Any,
        "role" to role as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactPermissions(
            active = map["active"] as? Boolean,
            contact_id = map["contact_id"] as? String,
            order_approval_limit = (map["order_approval_limit"] as? Number)?.toDouble(),
            organization_id = map["organization_id"] as? String,
            permissions = map["permissions"] as? List<String>,
            role = map["role"] as? String,
        )
    }
}