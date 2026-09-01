package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * The buying COMPANIES and everything keyed to one: the company rows themselves, their postal addresses, and the revenue/order projection pulled from the orders app. An organization is the unit a contract, a credit limit, a price list and a payment term belong to — not a person, and not a household. Addresses live here because a B2B address is the company's (a contact may own a private one, and that row is reached the same way). The people inside a company are in Contacts, and the groups a company falls into are in Segments.
 */
class CustomersOrganizations(client: Client) : Service(client) {

    /**
     * A postal address used for billing or for shipping, owned by exactly one of the two parties: an organization (the company address everyone in it may use) or a contact (a private one only that person uses). Both owner columns are nullable and exactly one is set — sending both, or neither, is refused. Every address this tenant holds, filterable by owner (`organization_id`, `contact_id`), by `type` and by any other column. It is how the addresses tab of a company or a person is filled; the page is `limit`/`offset`/`order`.
     *
     * @param id Filter to rows whose `id` is exactly this value. Primary key of the address.
     * @param organizationId Filter to one owning company.
     * @param contactId Filter to one owning contact — a personal address book.
     * @param type Filter by address type (GET /customers/address-types) — 'billing' or 'shipping' unless the merchant added their own.
     * @param company Filter to rows whose `company` is exactly this value. Company line on the label. Often the owning organization's name, but not always — a delivery to a construction site carries the site.
     * @param name Filter to rows whose `name` is exactly this value. Recipient line on the label — the person or department the parcel is addressed to.
     * @param street Filter to rows whose `street` is exactly this value. Street and house number, on one line, as the local post expects it.
     * @param street2 Filter to rows whose `street2` is exactly this value. The second address line: building, floor, gate, c/o. Null when there is none.
     * @param zip Filter to rows whose `zip` is exactly this value. Postal code, as text — leading zeros are real in most countries.
     * @param city Filter to rows whose `city` is exactly this value. City or town.
     * @param region Filter to rows whose `region` is exactly this value. State, province or Bundesland. Required by some destinations (US, CA), unused by most European ones.
     * @param country Filter by ISO 3166-1 alpha-2 country code.
     * @param phone Filter to rows whose `phone` is exactly this value. Phone number for the carrier to reach at this address — often a different one from the contact's own.
     * @param isDefault Filter to the default addresses. With `type` and an owner, this is the one address a checkout should preselect.
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the address was created.
     * @param updatedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When any column of this row last changed.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersAddressesList(
        id: String? = null,
        organizationId: String? = null,
        contactId: String? = null,
        type: String? = null,
        company: String? = null,
        name: String? = null,
        street: String? = null,
        street2: String? = null,
        zip: String? = null,
        city: String? = null,
        region: String? = null,
        country: String? = null,
        phone: String? = null,
        isDefault: Boolean? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/addresses"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "organization_id" to organizationId,
            "contact_id" to contactId,
            "type" to type,
            "company" to company,
            "name" to name,
            "street" to street,
            "street2" to street2,
            "zip" to zip,
            "city" to city,
            "region" to region,
            "country" to country,
            "phone" to phone,
            "is_default" to isDefault,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * A postal address used for billing or for shipping, owned by exactly one of the two parties: an organization (the company address everyone in it may use) or a contact (a private one only that person uses). Both owner columns are nullable and exactly one is set — sending both, or neither, is refused. `type` names one of this tenant's own address types — billing and shipping are seeded, and a merchant may add a works entrance or a central accounts office without a release of this app. `is_default` picks the one a checkout should preselect for that owner and that type. A create cannot omit `street`, `zip`, `city` and `country`; everything else is optional or defaulted by the database.
     *
     * @param city City or town.
     * @param country ISO 3166-1 alpha-2 country code, exactly two letters. Uppercase by convention; it is what shipping and tax both key off.
     * @param street Street and house number, on one line, as the local post expects it.
     * @param zip Postal code, as text — leading zeros are real in most countries.
     * @param company Company line on the label. Often the owning organization's name, but not always — a delivery to a construction site carries the site.
     * @param contactId Owning person — a personal address only that contact uses. Exactly one of organization_id / contact_id is set.
     * @param isDefault The default address of its owner AND type: one default billing and one default shipping address per owner. Setting it moves the flag off the previous holder. Default false.
     * @param name Recipient line on the label — the person or department the parcel is addressed to.
     * @param organizationId Owning company — a company address, shared by everyone in it. Exactly one of organization_id / contact_id is set.
     * @param phone Phone number for the carrier to reach at this address — often a different one from the contact's own.
     * @param region State, province or Bundesland. Required by some destinations (US, CA), unused by most European ones.
     * @param street2 The second address line: building, floor, gate, c/o. Null when there is none.
     * @param type What the address is FOR — one of the tenant's own address types (GET /customers/address-types), seeded with billing and shipping. A merchant may add their own (a works entrance, a central accounts office) without a release of this app. A create without it gets the type flagged as default; a type the tenant does not keep is a 400.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAddressesCreate(
        city: String,
        country: String,
        street: String,
        zip: String,
        company: String? = null,
        contactId: String? = null,
        isDefault: Boolean? = null,
        name: String? = null,
        organizationId: String? = null,
        phone: String? = null,
        region: String? = null,
        street2: String? = null,
        type: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/addresses"

        val apiParams = mutableMapOf<String, Any?>(
            "city" to city,
            "company" to company,
            "contact_id" to contactId,
            "country" to country,
            "is_default" to isDefault,
            "name" to name,
            "organization_id" to organizationId,
            "phone" to phone,
            "region" to region,
            "street" to street,
            "street2" to street2,
            "type" to type,
            "zip" to zip,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A postal address used for billing or for shipping, owned by exactly one of the two parties: an organization (the company address everyone in it may use) or a contact (a private one only that person uses). Both owner columns are nullable and exactly one is set — sending both, or neither, is refused. Removes the address. Orders already placed keep the address they were placed with; nothing in this app reaches back. Nothing else in this app points at it, so nothing else goes with it.
     *
     * @param id The address to delete.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAddressesDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/addresses/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A postal address used for billing or for shipping, owned by exactly one of the two parties: an organization (the company address everyone in it may use) or a contact (a private one only that person uses). Both owner columns are nullable and exactly one is set — sending both, or neither, is refused. One address by id, whichever of the two owners it hangs off.
     *
     * @param id The address to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAddressesGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/addresses/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A postal address used for billing or for shipping, owned by exactly one of the two parties: an organization (the company address everyone in it may use) or a contact (a private one only that person uses). Both owner columns are nullable and exactly one is set — sending both, or neither, is refused. A partial update — send only what changes. An empty body is refused rather than answered as a no-op, so a client that built the wrong patch finds out.
     *
     * @param id The address to update.
     * @param city City or town.
     * @param company Company line on the label. Often the owning organization's name, but not always — a delivery to a construction site carries the site.
     * @param contactId Owning person — a personal address only that contact uses. Exactly one of organization_id / contact_id is set.
     * @param country ISO 3166-1 alpha-2 country code, exactly two letters. Uppercase by convention; it is what shipping and tax both key off.
     * @param isDefault The default address of its owner AND type: one default billing and one default shipping address per owner. Setting it moves the flag off the previous holder. Default false.
     * @param name Recipient line on the label — the person or department the parcel is addressed to.
     * @param organizationId Owning company — a company address, shared by everyone in it. Exactly one of organization_id / contact_id is set.
     * @param phone Phone number for the carrier to reach at this address — often a different one from the contact's own.
     * @param region State, province or Bundesland. Required by some destinations (US, CA), unused by most European ones.
     * @param street Street and house number, on one line, as the local post expects it.
     * @param street2 The second address line: building, floor, gate, c/o. Null when there is none.
     * @param type What the address is FOR — one of the tenant's own address types (GET /customers/address-types), seeded with billing and shipping. A merchant may add their own (a works entrance, a central accounts office) without a release of this app. A create without it gets the type flagged as default; a type the tenant does not keep is a 400.
     * @param zip Postal code, as text — leading zeros are real in most countries.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAddressesUpdate(
        id: String,
        city: String? = null,
        company: String? = null,
        contactId: String? = null,
        country: String? = null,
        isDefault: Boolean? = null,
        name: String? = null,
        organizationId: String? = null,
        phone: String? = null,
        region: String? = null,
        street: String? = null,
        street2: String? = null,
        type: String? = null,
        zip: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/addresses/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "city" to city,
            "company" to company,
            "contact_id" to contactId,
            "country" to country,
            "is_default" to isDefault,
            "name" to name,
            "organization_id" to organizationId,
            "phone" to phone,
            "region" to region,
            "street" to street,
            "street2" to street2,
            "type" to type,
            "zip" to zip,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * What an organization has BOUGHT, materialized into this app from the orders app: lifetime revenue, revenue over the last 30/90/365 days, order count, average order value, and the first and last order dates. Revenue lives in orders and may not be joined (ADR-0055: no cross-app foreign key, grant or view), so it is pulled on a schedule and stored here — one row per organization, all-zero for a company that never ordered, so that a "never bought anything" rule has something to match. The customer-value list: sort by `revenue_365d` for the best customers, filter `last_order_at` for the dormant ones. Every row carries `computed_at`, and a row is only as current as the last refresh — `GET /customers/organization_metrics/freshness` says how stale the set is before a number is shown to anybody.
     *
     * @param id Filter to rows whose `id` is exactly this value. Primary key of the projection row.
     * @param organizationId Read the metrics of one company.
     * @param orderCount Filter to rows whose `order_count` is exactly this value. Orders ever counted for this company.
     * @param orderCount30d Filter to rows whose `order_count_30d` is exactly this value. Orders in the 30 days before `orders_as_of`. A rolling window, not a calendar month.
     * @param orderCount90d Filter to rows whose `order_count_90d` is exactly this value. Orders in the 90 days before `orders_as_of`.
     * @param orderCount365d Filter to rows whose `order_count_365d` is exactly this value. Orders in the 365 days before `orders_as_of`.
     * @param revenueTotal Filter to rows whose `revenue_total` is exactly this value. Revenue ever counted, in `currency`. Which orders count is the orders app's decision, not this app's.
     * @param revenue30d Filter to rows whose `revenue_30d` is exactly this value. Revenue in the 30 days before `orders_as_of`.
     * @param revenue90d Filter to rows whose `revenue_90d` is exactly this value. Revenue in the 90 days before `orders_as_of`.
     * @param revenue365d Filter to rows whose `revenue_365d` is exactly this value. Revenue in the 365 days before `orders_as_of`. The usual "how big is this customer" number, and the one a key-account rule should read.
     * @param avgOrderValue Filter to rows whose `avg_order_value` is exactly this value. revenue_total / order_count, computed here from the sums rather than averaged upstream. Zero when there are no orders.
     * @param avgOrderValue365d Filter to rows whose `avg_order_value_365d` is exactly this value. revenue_365d / order_count_365d. Zero when there were none in the window.
     * @param firstOrderAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When this company first ordered. Null if it never has — that is what makes it usable as "is this a customer at all?".
     * @param lastOrderAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When this company last ordered. Null if it never has, which is why the virtual `days_since_last_order` rule field never matches those companies: use `last_order_at is_empty` for them.
     * @param currency Filter to rows whose `currency` is exactly this value. The single ISO 4217 currency all counted orders were in. NULL when there were none, and also when there were several — read `currency_mixed` to tell those two apart.
     * @param currencyMixed Filter to rows whose `currency_mixed` is exactly this value. True when this company ordered in more than one currency. The sums are still stored (dropping money is worse), but they are not comparable against a threshold, and a rule reading revenue should say so.
     * @param ordersAsOf Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. The instant the rolling windows were measured from. Pinned across a chunked refresh, so a multi-call pass cannot let the windows slide underneath it.
     * @param computedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When this row was last written. The projection is materialized, so this is how stale the numbers are.
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the projection row first appeared.
     * @param updatedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the row last changed. Unchanged numbers are not rewritten, so this can lag `computed_at`.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersOrganizationMetricsList(
        id: String? = null,
        organizationId: String? = null,
        orderCount: Long? = null,
        orderCount30d: Long? = null,
        orderCount90d: Long? = null,
        orderCount365d: Long? = null,
        revenueTotal: Double? = null,
        revenue30d: Double? = null,
        revenue90d: Double? = null,
        revenue365d: Double? = null,
        avgOrderValue: Double? = null,
        avgOrderValue365d: Double? = null,
        firstOrderAt: String? = null,
        lastOrderAt: String? = null,
        currency: String? = null,
        currencyMixed: Boolean? = null,
        ordersAsOf: String? = null,
        computedAt: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/organization_metrics"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "organization_id" to organizationId,
            "order_count" to orderCount,
            "order_count_30d" to orderCount30d,
            "order_count_90d" to orderCount90d,
            "order_count_365d" to orderCount365d,
            "revenue_total" to revenueTotal,
            "revenue_30d" to revenue30d,
            "revenue_90d" to revenue90d,
            "revenue_365d" to revenue365d,
            "avg_order_value" to avgOrderValue,
            "avg_order_value_365d" to avgOrderValue365d,
            "first_order_at" to firstOrderAt,
            "last_order_at" to lastOrderAt,
            "currency" to currency,
            "currency_mixed" to currencyMixed,
            "orders_as_of" to ordersAsOf,
            "computed_at" to computedAt,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * The projection is materialized, so it is only as true as its last refresh. This is that fact as one answer: the OLDEST computed_at in the table (the floor, not an average), the anchor those numbers were measured from, and how many organizations are not covered at all yet.
     *
     * @return [com.revenexx.models.OrganizationMetricsFreshness]
     */
    suspend fun customersOrganizationMetricsFreshness(
    ): com.revenexx.models.OrganizationMetricsFreshness {
        val apiPath = "/v1/customers/organization_metrics/freshness"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.OrganizationMetricsFreshness = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrganizationMetricsFreshness.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrganizationMetricsFreshness::class.java,
            converter,
        )
    }


    /**
     * Revenue lives in the orders app and cannot be joined (ADR-0055: no cross-app FK, grant or view), so it is PULLED: this route walks organizations in id order, asks orders.reports.customer-rollup about a batch of them at a time and materializes the answer into organization_metrics — one row per organization, all-zero for those that never ordered, so that 'never bought' rules match something. Rows are only rewritten when a value actually changed, so a routine refresh costs almost no writes. Bounded by a wall-clock budget below the gateway's upstream timeout: while 'done' is false, POST again with the returned 'cursor' AND 'as_of' (pinning as_of is what stops the rolling windows sliding during a multi-call refresh). 'organization_ids' refreshes exactly those organizations in a single call — the targeted path after a customer ordered.
     *
     * @param asOf Anchor for the rolling windows — pass back the value the previous call returned.
     * @param cursor Continue an unfinished refresh: the value the previous call returned, verbatim. It is the id of the last organization processed, so only a value this API handed out ever resolves.
     * @param organizationIds Refresh exactly these organizations in one call instead of walking all of them.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersOrganizationMetricsRefresh(
        asOf: String? = null,
        cursor: String? = null,
        organizationIds: List<String>? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organization_metrics/refresh"

        val apiParams = mutableMapOf<String, Any?>(
            "as_of" to asOf,
            "cursor" to cursor,
            "organization_ids" to organizationIds,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * What an organization has BOUGHT, materialized into this app from the orders app: lifetime revenue, revenue over the last 30/90/365 days, order count, average order value, and the first and last order dates. Revenue lives in orders and may not be joined (ADR-0055: no cross-app foreign key, grant or view), so it is pulled on a schedule and stored here — one row per organization, all-zero for a company that never ordered, so that a "never bought anything" rule has something to match. One company's numbers by the metrics row id. All zeroes mean the company has never ordered, not that the projection is missing — a missing row means the refresh has not reached that company yet.
     *
     * @param id The organization metrics row to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersOrganizationMetricsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organization_metrics/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * An organization is a buying COMPANY — the unit a contract, a credit limit, a price list and a payment term belong to, and the unit an order is placed on behalf of. It is not a household and not a person: the people are `contacts`, and a company with no contacts yet is a perfectly normal row. Every organization is mirrored into platform auth as a team, so a name written here is the name storefront authentication shows. The company list a sales or service desk works from, and the read a segment rule is written against. Every column of the table is a filter and the page is `limit`/`offset`/`order` — including the two that are constantly confused: `status` is ACCESS (active or blocked) and `lifecycle_stage` is the sales PIPELINE, so filtering the wrong one answers with the wrong companies rather than with an error.
     *
     * @param id Filter to exactly one company. `GET /customers/organizations/{id}` is the direct form; this exists because the list honours it too.
     * @param name Filter by the EXACT company name — this is an equality, not a search. There is no substring or fuzzy match on this API.
     * @param vatId Look a company up by its VAT id — the check an integration runs before founding a duplicate.
     * @param branche Filter by exact industry. Free text a merchant typed, matched exactly and case-sensitively — 'Maschinenbau' does not find 'maschinenbau', and there is no substring search to fall back on.
     * @param customerNumber Look a company up by its ERP number — the lookup an ERP integration and a service desk both start from. Exact match; the real numbers come from the merchant, so the example here resolves nowhere.
     * @param status Filter by status — access, not pipeline.
     * @param lifecycleStage Filter by pipeline stage. One of the tenant's own stages (GET /customers/lifecycle-stages); a fresh install starts with lead, prospect, customer, churned.
     * @param paymentTerms Filter to rows whose `payment_terms` is exactly this value. When this company has to pay — one of the tenant's own terms (GET /customers/payment-terms, seeded with prepayment, direct_debit, net_7/14/30/60/90). Null means nothing was agreed and the order flow falls back to the market's `default_payment_terms`. This is a commercial term, not a payment method: HOW they pay is the payments app's business.
     * @param creditLimit Filter to rows whose `credit_limit` is exactly this value. Ceiling on open receivables in the market's currency, and one of the inputs that decide whether an order is accepted at all. Null means NO limit — not a limit of zero.
     * @param priceList Filter to rows whose `price_list` is exactly this value. Code of the price list this company buys on — plain text pointing into the prices app. ADR-0055 forbids the cross-app foreign key, so nothing here checks it: a code that names no list simply prices nothing. `standard` is the list the prices app seeds on install.
     * @param deliveryBlock Filter to companies whose shipments are stopped.
     * @param externalTeamId Find the organization behind a platform team id. The reverse of the mirror, and the way an auth-side id becomes a customer record.
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When this company record was created in this app. Not when the customer relationship began — an ERP import creates decade-old customers today.
     * @param updatedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When any column of this row last changed.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersOrganizationsList(
        id: String? = null,
        name: String? = null,
        vatId: String? = null,
        branche: String? = null,
        customerNumber: String? = null,
        status: com.revenexx.enums.CustomersOrganizationsListStatus? = null,
        lifecycleStage: String? = null,
        paymentTerms: String? = null,
        creditLimit: Double? = null,
        priceList: String? = null,
        deliveryBlock: Boolean? = null,
        externalTeamId: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/organizations"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "name" to name,
            "vat_id" to vatId,
            "branche" to branche,
            "customer_number" to customerNumber,
            "status" to status,
            "lifecycle_stage" to lifecycleStage,
            "payment_terms" to paymentTerms,
            "credit_limit" to creditLimit,
            "price_list" to priceList,
            "delivery_block" to deliveryBlock,
            "external_team_id" to externalTeamId,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * An organization is a buying COMPANY — the unit a contract, a credit limit, a price list and a payment term belong to, and the unit an order is placed on behalf of. It is not a household and not a person: the people are `contacts`, and a company with no contacts yet is a perfectly normal row. Every organization is mirrored into platform auth as a team, so a name written here is the name storefront authentication shows. Registers a company as a customer. It is mirrored into platform auth as a team in the same call, so a failure of the identity service fails the create rather than leaving half a company behind. `payment_terms` and `lifecycle_stage` name values from this tenant's own sets, and a newly founded company inherits the tenant's `default_payment_terms` / `default_credit_limit` where the merchant set them. `name` is the only field a create cannot omit; everything else is optional or defaulted by the database. Two rows of this tenant may not share `customer_number` (while customer_number IS NOT NULL) or `external_team_id` (while external_team_id IS NOT NULL).
     *
     * @param name Legal or trading name of the COMPANY — never a person. Mirrored to the platform team, so a rename here is a rename in storefront auth too.
     * @param branche Industry / line of business, in the merchant's own words. Free text: no NACE code, no WZ number, no list to pick from — whatever somebody typed on the company. Segment rules read it, and both `?branche=` and an `eq` condition match it EXACTLY and case-sensitively, so 'Maschinenbau' and 'maschinenbau' are two different industries. Indexed, so it stays cheap to filter on.
     * @param creditLimit Ceiling on open receivables in the market's currency, and one of the inputs that decide whether an order is accepted at all. Null means NO limit — not a limit of zero. A create without it inherits the tenant's `default_credit_limit`.
     * @param customerNumber The number this company carries in the merchant's own ERP — the key an ERP integration joins on, and what a service desk asks for on the phone. Free text with NO enforced format (a letter prefix and a running number is the common shape, but plain digits are just as valid), unique per tenant while it is set, and one of the fields duplicate detection can be pointed at. The real values come out of the merchant's ERP; nothing published here can name one that exists. A second company with the same number is a 409.
     * @param deliveryBlock True stops SHIPMENTS to this company while leaving login and ordering alone — the "they may order, we are just not sending anything until this is settled" state. Separate from `status` on purpose: blocking the login to stop a delivery locks out the people who could settle it. Default false.
     * @param lifecycleStage Where the company stands in the SALES PIPELINE, and a deliberately separate axis from `status`: a prospect that may log in and a customer that may not are both ordinary states, and one column cannot say that. One of the tenant's own stages (GET /customers/lifecycle-stages) — a fresh install starts with lead, prospect, customer, churned, and the merchant may add their own. Nothing moves it automatically; a stage changes when a person or an integration says so. A create without it gets the stage flagged as default; a value the tenant does not keep is a 400.
     * @param paymentTerms When this company has to pay — one of the tenant's own terms (GET /customers/payment-terms, seeded with prepayment, direct_debit, net_7/14/30/60/90). Null means nothing was agreed and the order flow falls back to the market's `default_payment_terms`. This is a commercial term, not a payment method: HOW they pay is the payments app's business. A create without it inherits the market's `default_payment_terms`; a value the tenant does not keep is a 400.
     * @param priceList Code of the price list this company buys on — plain text pointing into the prices app. ADR-0055 forbids the cross-app foreign key, so nothing here checks it: a code that names no list simply prices nothing. `standard` is the list the prices app seeds on install.
     * @param settings Free-form per-organization settings, keyed by whatever the merchant's own integrations agree on — this app never branches on a key in here. Segment rules can address a TOP-LEVEL key as `setting:<key>`, which is the whole reason the blob survives: a flag an ERP writes here selects a segment without a schema change. Commercial terms are typed columns now (payment_terms, credit_limit); writing them back in here leaves the checkout reading the column and finding nothing. Replaced wholesale on an update — send the whole object, not a patch of it.
     * @param status ACCESS, not pipeline: 'blocked' stops this company's people from logging in and is where a rejected registration parks the company it founded. 'active' is the default. For how far along a company is, read `lifecycle_stage` — reading this one for that is how a won deal gets locked out. Default 'active'.
     * @param vatId VAT identification number (USt-IdNr. in Germany) — the closest thing a B2B buyer has to a legal identity. Validated against the EU VIES service when the tenant's `organization_vat_id_required` setting is on, and stored verbatim otherwise, including for buyers outside the EU.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersOrganizationsCreate(
        name: String,
        branche: String? = null,
        creditLimit: Double? = null,
        customerNumber: String? = null,
        deliveryBlock: Boolean? = null,
        lifecycleStage: String? = null,
        paymentTerms: String? = null,
        priceList: String? = null,
        settings: Any? = null,
        status: com.revenexx.enums.OrganizationStatus? = null,
        vatId: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organizations"

        val apiParams = mutableMapOf<String, Any?>(
            "branche" to branche,
            "credit_limit" to creditLimit,
            "customer_number" to customerNumber,
            "delivery_block" to deliveryBlock,
            "lifecycle_stage" to lifecycleStage,
            "name" to name,
            "payment_terms" to paymentTerms,
            "price_list" to priceList,
            "settings" to settings,
            "status" to status,
            "vat_id" to vatId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * An organization is a buying COMPANY — the unit a contract, a credit limit, a price list and a payment term belong to, and the unit an order is placed on behalf of. It is not a household and not a person: the people are `contacts`, and a company with no contacts yet is a perfectly normal row. Every organization is mirrored into platform auth as a team, so a name written here is the name storefront authentication shows. Removes the company and its mirrored team. Its people are NOT deleted: they become standalone buyers who can still sign in and still order, which is the behaviour a merchant winding down a subsidiary wants. Deleting one takes every `contact_events`, `addresses`, `organization_metrics` and `segment_members` row that points at it with it and clears `contacts.organization_id` rather than deleting those rows — the foreign keys decide, not this route.
     *
     * @param id The organization to delete.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersOrganizationsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organizations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * An organization is a buying COMPANY — the unit a contract, a credit limit, a price list and a payment term belong to, and the unit an order is placed on behalf of. It is not a household and not a person: the people are `contacts`, and a company with no contacts yet is a perfectly normal row. Every organization is mirrored into platform auth as a team, so a name written here is the name storefront authentication shows. One company by id, with its commercial terms as stored. What it has BOUGHT is not in here — that is the `organization_metrics` row for the same id, refreshed on its own schedule.
     *
     * @param id The organization to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersOrganizationsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organizations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * An organization is a buying COMPANY — the unit a contract, a credit limit, a price list and a payment term belong to, and the unit an order is placed on behalf of. It is not a household and not a person: the people are `contacts`, and a company with no contacts yet is a perfectly normal row. Every organization is mirrored into platform auth as a team, so a name written here is the name storefront authentication shows. A partial update — send only what changes. `external_team_id` is mirror-managed and ignored if sent. Blocking a company here is what stops it trading; moving it through the pipeline is `lifecycle_stage`, and the two are independent. Two rows of this tenant may not share `customer_number` (while customer_number IS NOT NULL) or `external_team_id` (while external_team_id IS NOT NULL).
     *
     * @param id The organization to update.
     * @param branche Industry / line of business, in the merchant's own words. Free text: no NACE code, no WZ number, no list to pick from — whatever somebody typed on the company. Segment rules read it, and both `?branche=` and an `eq` condition match it EXACTLY and case-sensitively, so 'Maschinenbau' and 'maschinenbau' are two different industries. Indexed, so it stays cheap to filter on.
     * @param creditLimit Ceiling on open receivables in the market's currency, and one of the inputs that decide whether an order is accepted at all. Null means NO limit — not a limit of zero. A create without it inherits the tenant's `default_credit_limit`.
     * @param customerNumber The number this company carries in the merchant's own ERP — the key an ERP integration joins on, and what a service desk asks for on the phone. Free text with NO enforced format (a letter prefix and a running number is the common shape, but plain digits are just as valid), unique per tenant while it is set, and one of the fields duplicate detection can be pointed at. The real values come out of the merchant's ERP; nothing published here can name one that exists. A second company with the same number is a 409.
     * @param deliveryBlock True stops SHIPMENTS to this company while leaving login and ordering alone — the "they may order, we are just not sending anything until this is settled" state. Separate from `status` on purpose: blocking the login to stop a delivery locks out the people who could settle it. Default false.
     * @param lifecycleStage Where the company stands in the SALES PIPELINE, and a deliberately separate axis from `status`: a prospect that may log in and a customer that may not are both ordinary states, and one column cannot say that. One of the tenant's own stages (GET /customers/lifecycle-stages) — a fresh install starts with lead, prospect, customer, churned, and the merchant may add their own. Nothing moves it automatically; a stage changes when a person or an integration says so. A create without it gets the stage flagged as default; a value the tenant does not keep is a 400.
     * @param name Legal or trading name of the COMPANY — never a person. Mirrored to the platform team, so a rename here is a rename in storefront auth too.
     * @param paymentTerms When this company has to pay — one of the tenant's own terms (GET /customers/payment-terms, seeded with prepayment, direct_debit, net_7/14/30/60/90). Null means nothing was agreed and the order flow falls back to the market's `default_payment_terms`. This is a commercial term, not a payment method: HOW they pay is the payments app's business. A create without it inherits the market's `default_payment_terms`; a value the tenant does not keep is a 400.
     * @param priceList Code of the price list this company buys on — plain text pointing into the prices app. ADR-0055 forbids the cross-app foreign key, so nothing here checks it: a code that names no list simply prices nothing. `standard` is the list the prices app seeds on install.
     * @param settings Free-form per-organization settings, keyed by whatever the merchant's own integrations agree on — this app never branches on a key in here. Segment rules can address a TOP-LEVEL key as `setting:<key>`, which is the whole reason the blob survives: a flag an ERP writes here selects a segment without a schema change. Commercial terms are typed columns now (payment_terms, credit_limit); writing them back in here leaves the checkout reading the column and finding nothing. Replaced wholesale on an update — send the whole object, not a patch of it.
     * @param status ACCESS, not pipeline: 'blocked' stops this company's people from logging in and is where a rejected registration parks the company it founded. 'active' is the default. For how far along a company is, read `lifecycle_stage` — reading this one for that is how a won deal gets locked out. Default 'active'.
     * @param vatId VAT identification number (USt-IdNr. in Germany) — the closest thing a B2B buyer has to a legal identity. Validated against the EU VIES service when the tenant's `organization_vat_id_required` setting is on, and stored verbatim otherwise, including for buyers outside the EU.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersOrganizationsUpdate(
        id: String,
        branche: String? = null,
        creditLimit: Double? = null,
        customerNumber: String? = null,
        deliveryBlock: Boolean? = null,
        lifecycleStage: String? = null,
        name: String? = null,
        paymentTerms: String? = null,
        priceList: String? = null,
        settings: Any? = null,
        status: com.revenexx.enums.OrganizationStatus? = null,
        vatId: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organizations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "branche" to branche,
            "credit_limit" to creditLimit,
            "customer_number" to customerNumber,
            "delivery_block" to deliveryBlock,
            "lifecycle_stage" to lifecycleStage,
            "name" to name,
            "payment_terms" to paymentTerms,
            "price_list" to priceList,
            "settings" to settings,
            "status" to status,
            "vat_id" to vatId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}