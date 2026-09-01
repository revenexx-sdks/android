package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The product as the buyer was shown it when this line was added — the cart's own copy, so it stays honest when the catalogue moves underneath it. Free-form apart from the price: conversion reads `unit_price` (or `price` as a fallback) and nothing else. A snapshot without a readable price leaves the line alone in both price modes, which is deliberate — a missing snapshot must never be read as "free".
 */
data class CartItemSnapshot<T>(
    /**
     * The older spelling of the same thing, read only when `unit_price` is absent.
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * The net unit price the buyer was shown. This is what carts.order books the line on under price_snapshot_mode = snapshot, and what it rewrites under = live.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "price" to price as Any,
        "unit_price" to unit_price as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            price: Double?,
            unit_price: Double?,
            data: Map<String, Any>
        ) = CartItemSnapshot<Map<String, Any>>(
            price,
            unit_price,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = CartItemSnapshot<T>(
            price = (map["price"] as? Number)?.toDouble(),
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}