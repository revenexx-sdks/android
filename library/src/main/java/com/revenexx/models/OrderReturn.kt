package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderReturnStatus

/**
 * Goods coming BACK, with their own lifecycle: registered → received → completed | rejected. Only completing books anything onto the positions; registering and receiving are announcements.
 */
data class OrderReturn(
    /**
     * When the return was settled, stamped by the SERVER. Never taken from the body: a client clock records when a client thinks it acted, not when the goods were booked.
     */
    @SerializedName("completed_at")
    var completed_at: String?,

    /**
     * When the return row was written.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the return. The {rid} segment of the return routes.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Free-form data for the caller — the returns portal's own reference. Stored and returned untouched.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The RETURN number — drawn from the tenant's return range, unique per tenant, and a third series alongside orders and delivery notes. What the customer writes on the parcel.
     */
    @SerializedName("number")
    var number: String?,

    /**
     * The order the goods are coming back from. A return of another order is a 404 on these routes, not a cross-order write.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * The positions and quantities this return covers, fixed when it was registered and guarded against the shipped-but-not-yet-returned quantity of each. Entries flagged restock are what the completion reports back for the inventories call.
     */
    @SerializedName("positions")
    var positions: List<OrderReturnedPosition>?,

    /**
     * Why the goods are coming back, free text as the customer or the desk stated it. Also what /reject stores when it is given no resolution out of the published set.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * When the goods physically arrived back. Null until POST …/receive — and null forever on a return that was completed straight out of registered, which is allowed.
     */
    @SerializedName("received_at")
    var received_at: String?,

    /**
     * When the return was announced. Defaults to now.
     */
    @SerializedName("registered_at")
    var registered_at: String?,

    /**
     * When the return was refused. Null unless it was.
     */
    @SerializedName("rejected_at")
    var rejected_at: String?,

    /**
     * How it ended, in one of the words this app publishes — the settlement words on a completion (refund, partial_refund, replacement, repair, store_credit), the refusal words on a rejection (wear_and_tear, not_returnable); GET /orders/vocabularies/return-resolutions carries both sets with the stage that accepts each. The column carries no database constraint; the ROUTES enforce the set, which is what stopped a client settling returns with a word nobody else knew. On a rejection that named no resolution, the free-text reason is stored here instead — which is the one case a value outside the two sets appears.
     */
    @SerializedName("resolution")
    var resolution: String?,

    /**
     * Where the return stands: 'registered' = announced, nothing booked; 'received' = the goods are back but not yet settled; 'completed' = settled, and the only transition that books quantity_returned; 'rejected' = refused, nothing booked. The last two are final.
     */
    @SerializedName("status")
    var status: OrderReturnStatus?,

    /**
     * When the return last changed — each of its transitions writes it.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "completed_at" to completed_at as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "metadata" to metadata as Any,
        "number" to number as Any,
        "order_id" to order_id as Any,
        "positions" to positions?.map { it.toMap() } as Any,
        "reason" to reason as Any,
        "received_at" to received_at as Any,
        "registered_at" to registered_at as Any,
        "rejected_at" to rejected_at as Any,
        "resolution" to resolution as Any,
        "status" to status?.value as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturn(
            completed_at = map["completed_at"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            order_id = map["order_id"] as? String,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderReturnedPosition.from(map = it) },
            reason = map["reason"] as? String,
            received_at = map["received_at"] as? String,
            registered_at = map["registered_at"] as? String,
            rejected_at = map["rejected_at"] as? String,
            resolution = map["resolution"] as? String,
            status = OrderReturnStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            updated_at = map["updated_at"] as? String,
        )
    }
}