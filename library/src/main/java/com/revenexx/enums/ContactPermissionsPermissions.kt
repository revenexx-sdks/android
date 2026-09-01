package com.revenexx.enums

import com.google.gson.annotations.SerializedName

enum class ContactPermissionsPermissions(val value: String) {
    @SerializedName("catalog.read")
    CATALOG_READ("catalog.read"),
    @SerializedName("carts.manage")
    CARTS_MANAGE("carts.manage"),
    @SerializedName("orders.create")
    ORDERS_CREATE("orders.create"),
    @SerializedName("orders.request")
    ORDERS_REQUEST("orders.request"),
    @SerializedName("orders.approve")
    ORDERS_APPROVE("orders.approve"),
    @SerializedName("orders.read")
    ORDERS_READ("orders.read"),
    @SerializedName("addresses.manage")
    ADDRESSES_MANAGE("addresses.manage"),
    @SerializedName("contacts.read")
    CONTACTS_READ("contacts.read"),
    @SerializedName("contacts.manage")
    CONTACTS_MANAGE("contacts.manage"),
    @SerializedName("organization.manage")
    ORGANIZATION_MANAGE("organization.manage");

    override fun toString() = value
}