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
 * WHAT is offered, what it costs, and the answer a checkout gets. A shipping method is the line a buyer picks: a pricing model ('fixed', 'free' or 'matrix'), the countries it may be offered into, a free-above threshold, and the carrier it ships with. A matrix method prices off its own rate tiers — a lookup table of `from_value` → price, nested under the method, deleted with it — which is why they are one group and not two: a method with pricing_type 'matrix' and no tiers quotes nothing at all. POST /shipping/rates is the read side of everything in here: it takes the buyer context and answers with the methods that apply and their computed prices, plus an `excluded` list naming the ones that did not and why. The delivery promise on that answer is inherited from the carrier and is described under that group.
 */
class ShippingMethods(client: Client) : Service(client) {

    /**
     * Filterable by exact column value — `?code=`, `?enabled=`, `?pricing_type=`, `?carrier_id=`, `?carrier=` and `?tax_class=` are applied as equalities and echoed back in `filter`. `?carrier_id=` and `?carrier=` are the two halves of one question: the first finds the methods holding a reference, the second the ones still resolving through the legacy code text. A query key that names no column of this entity is SILENTLY IGNORED — `?status=` on this route is the trap, since carriers have a status and methods do not: the page comes back unfiltered, 200, with an empty `filter`.
     *
     * @param limit Page size (default 50, max 200). A value outside the range is clamped rather than refused, and `page.limit` echoes what was applied.
     * @param offset Row offset for pagination (default 0). The next page is `page.offset + page.returned`.
     * @param order Sort as 'column.asc' | 'column.desc' — a bare 'column' sorts ascending. The column must be one this entity has; anything else is a 400 from the data plane.
     * @param code Exact-match filter on `code`. Unique per tenant, so this resolves a code a checkout already holds without paging the whole list.
     * @param enabled Exact-match filter on `enabled`. Only enabled methods are ever quoted, so this is the storefront-facing subset.
     * @param pricingType Exact-match filter on `pricing_type`. Pricing model — `matrix` is the set whose tiers a rate-matrix editor has to load.
     * @param carrierId Exact-match filter on `carrier_id`. The methods that ship with one carrier — what a merchant needs before pausing it. Matches `carrier_id` only, never the legacy `carrier` text.
     * @param carrier Exact-match filter on `carrier`. The other half of that question: the methods still resolving their carrier through the legacy free-text CODE rather than a reference. Together with `?carrier_id=` this is how a merchant finds what a carrier is still holding before retiring it.
     * @param taxClass Exact-match filter on `tax_class`. The methods naming one tax class — the same question GET /shipping/tax-classes/{code}/usage counts, when the caller wants the rows rather than the count. Only a method's OWN class; a method falling back to the tenant setting does not match.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingMethodsList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        code: String? = null,
        enabled: Boolean? = null,
        pricingType: com.revenexx.enums.PricingType? = null,
        carrierId: String? = null,
        carrier: String? = null,
        taxClass: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "code" to code,
            "enabled" to enabled,
            "pricing_type" to pricingType,
            "carrier_id" to carrierId,
            "carrier" to carrier,
            "tax_class" to taxClass,
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
     * A shipping method is the line a buyer picks in the checkout: a pricing model ('fixed', 'free' or 'matrix'), the countries it may be offered into, a free-above threshold, and the carrier it ships with. The method owns the PRICE; the delivery promise — tracking template, cut-off, handling and transit days — is inherited from the carrier wherever the method states none of its own. A create cannot omit `code` and `name`; every other column is optional or defaulted by the database. Two rows of this tenant may not share `code` — that is the 409. The new method is quoted by nobody until two further things are true: `enabled` defaults to FALSE, and a 'matrix' method has no tiers yet — until POST or PUT …/tiers gives it some it appears in `excluded` with 'matrix has no rate tiers configured' rather than in the rates. `carrier_id` and the legacy `carrier` code are both accepted and neither is verified against the carrier table here: an unmatched code is a plain carrier name on the rate, not an error.
     *
     * @param code Stable method code, unique per tenant (e.g. standard, express). What a checkout and an order line store, so it is the value every integration joins on.
     * @param name Display name shown in the checkout.
     * @param carrier Carrier CODE, kept from before shipping_carriers existed. Looked up in the carrier table when carrier_id is not set, so an existing value keeps working and gains a tracking template; a code nobody maintains is still reported as a plain name.
     * @param carrierId The carrier this method ships with. Wins over `carrier` and supplies the tracking template, pickup cut-off, handling time and transit days.
     * @param countries The countries this method may be offered into. ISO 3166-1 alpha-2 codes; null or an empty array means no restriction. Compared upper-cased, so a lower-case entry still matches. Declared as an array rather than the bare object a jsonb column derives to — this one is always a list. ANDed with the carrier's own reach.
     * @param currency ISO 4217 code (default EUR). Exactly three characters — the column says so. Echoed into a rate, never converted: this app prices in the currency the method carries.
     * @param description The sentence under the name in the checkout — the delivery promise in words. Null when the name says enough.
     * @param enabled Only enabled methods are ever quoted (default false); a disabled one is reported in `excluded` rather than hidden.
     * @param etaDaysMax Transit time upper bound in calendar days. Falls back to the carrier's when null.
     * @param etaDaysMin Transit time lower bound in calendar days, for the checkout. Falls back to the carrier's when null.
     * @param freeAbove Free shipping at or above this order value — wins over every pricing model, including a matrix. Compared net or gross as the market's free_above_compares setting declares. Null falls back to the tenant's shop-wide free_shipping_threshold.
     * @param labels Localized display names. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     * @param matrixAttribute Attribute name for matrix_basis 'attribute' — the key the rate request's `attributes` map is read at. Free text: the set of attributes is the catalogue's, not this app's.
     * @param matrixBasis The measure a matrix method prices its tiers over: total basket weight (in the market's weight unit), total item count, order value, or 'attribute' — any number the rate request carries under matrix_attribute. Null falls back to the tenant's matrix_basis_default. Ignored unless pricing_type is 'matrix'.
     * @param metadata Free-form jsonb the platform never reads or validates — whatever the merchant or their integration needs to keep beside the row (a customer number with the carrier, an ERP key, a label-printer id). The shape varies BY INTEGRATION, not by anything this app knows, so no key is declared and none is reserved; the example is one plausible instance rather than a schema. A flat map of scalars is the convention, and nothing enforces it.
     * @param position Sort order in the checkout (default 0) — a rate answer is returned in this order.
     * @param price The fixed price (default 0), in `currency` — ignored for 'free' and 'matrix'.
     * @param pricingType Pricing model (default 'fixed'): 'fixed' is one price for every basket, 'free' is no price at all, 'matrix' is a tiered price read off this method's rate tiers. Only 'matrix' looks at matrix_basis, quote_above and the tier table.
     * @param quoteAbove Above this MATRIX MEASURE the method carries no automatic price: it is still offered, flagged `quote_required` with a reason, and the storefront shows 'shipping on request'. For bulky or overweight freight priced by hand. Null = every measure is priced automatically.
     * @param taxClass This method's own tax class, as a CODE into the buyer market's tax classes (markets.tax_classes) — never a rate. First step of the tax chain: unset falls back to the tenant's shipping_tax_class setting, then the market default. Not a foreign key and it could not be (ADR-0055); GET /shipping/tax-classes/{code}/usage is the integrity question markets asks in its place.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingMethodsCreate(
        code: String,
        name: String,
        carrier: String? = null,
        carrierId: String? = null,
        countries: List<String>? = null,
        currency: String? = null,
        description: String? = null,
        enabled: Boolean? = null,
        etaDaysMax: Long? = null,
        etaDaysMin: Long? = null,
        freeAbove: Double? = null,
        labels: Any? = null,
        matrixAttribute: String? = null,
        matrixBasis: com.revenexx.enums.ShippingMethodMatrixBasis? = null,
        metadata: Any? = null,
        position: Long? = null,
        price: Double? = null,
        pricingType: com.revenexx.enums.ShippingMethodPricingType? = null,
        quoteAbove: Double? = null,
        taxClass: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods"

        val apiParams = mutableMapOf<String, Any?>(
            "carrier" to carrier,
            "carrier_id" to carrierId,
            "code" to code,
            "countries" to countries,
            "currency" to currency,
            "description" to description,
            "enabled" to enabled,
            "eta_days_max" to etaDaysMax,
            "eta_days_min" to etaDaysMin,
            "free_above" to freeAbove,
            "labels" to labels,
            "matrix_attribute" to matrixAttribute,
            "matrix_basis" to matrixBasis,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "price" to price,
            "pricing_type" to pricingType,
            "quote_above" to quoteAbove,
            "tax_class" to taxClass,
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
     * Runs the carrier seed first, then creates any missing method: the three lines a shop is expected to offer — standard, express and pickup. The app runs this itself on `app.installed`, so a fresh install already has them; calling it by hand afterwards is how a tenant that deleted one gets it back, and calling it twice costs nothing, because it reconciles rather than seeds. The seeded methods deliberately name no carrier: which carrier carries the standard method is a contract, not a default, and a method that says 'dhl' resolves to the seeded DHL row anyway.
     *
     * @return [Any]
     */
    suspend fun shippingMethodsDefaults(
    ): Any {
        val apiPath = "/v1/shipping/methods/defaults"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Deleting one takes every `shipping_rate_tiers` row that points at it with it — the foreign keys decide that, not this route. So the whole rate matrix goes with the method, which is also why this never answers a conflict and why there is no way to recover the table afterwards — for a method a checkout may still be holding in a session, `enabled: false` is the safer edit.
     *
     * @param id The row id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun shippingMethodsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{id}"
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
     * A shipping method is the line a buyer picks in the checkout: a pricing model ('fixed', 'free' or 'matrix'), the countries it may be offered into, a free-above threshold, and the carrier it ships with. The method owns the PRICE; the delivery promise — tracking template, cut-off, handling and transit days — is inherited from the carrier wherever the method states none of its own. This is the CONFIGURATION of one, by row id — not what a buyer would be charged. A matrix method's prices are not in here at all: they are its rate tiers, GET /shipping/methods/{method_id}/tiers, and the price for a given basket is POST /shipping/rates, which is the only place free-above thresholds, country restrictions, the carrier's reach and tax are applied. A checkout that reads `price` off this row prices a matrix method at 0.
     *
     * @param id The row id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun shippingMethodsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{id}"
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
     * A shipping method is the line a buyer picks in the checkout: a pricing model ('fixed', 'free' or 'matrix'), the countries it may be offered into, a free-above threshold, and the carrier it ships with. The method owns the PRICE; the delivery promise — tracking template, cut-off, handling and transit days — is inherited from the carrier wherever the method states none of its own. A partial update — send only what changes, whether that is taking the method in or out of the checkout, its pricing, the countries it is restricted to or the delivery estimate it states of its own; a payload carrying no column at all is refused rather than answering a row it did not touch. Flipping `enabled` is what puts the method in front of a buyer or takes it away, and a disabled method is reported in the rate answer's `excluded` rather than hidden. Changing `pricing_type` away from 'matrix' does NOT delete the tier table — it stops being read, and changing back reinstates the old prices, so a method switched to 'fixed' and back quotes what it quoted before. Two rows of this tenant may not share `code` — that is the 409.
     *
     * @param id The row id.
     * @param carrier Carrier CODE, kept from before shipping_carriers existed. Looked up in the carrier table when carrier_id is not set, so an existing value keeps working and gains a tracking template; a code nobody maintains is still reported as a plain name.
     * @param carrierId The carrier this method ships with. Wins over `carrier` and supplies the tracking template, pickup cut-off, handling time and transit days.
     * @param code Stable method code, unique per tenant (e.g. standard, express). What a checkout and an order line store, so it is the value every integration joins on.
     * @param countries The countries this method may be offered into. ISO 3166-1 alpha-2 codes; null or an empty array means no restriction. Compared upper-cased, so a lower-case entry still matches. Declared as an array rather than the bare object a jsonb column derives to — this one is always a list. ANDed with the carrier's own reach.
     * @param currency ISO 4217 code (default EUR). Exactly three characters — the column says so. Echoed into a rate, never converted: this app prices in the currency the method carries.
     * @param description The sentence under the name in the checkout — the delivery promise in words. Null when the name says enough.
     * @param enabled Only enabled methods are ever quoted (default false); a disabled one is reported in `excluded` rather than hidden.
     * @param etaDaysMax Transit time upper bound in calendar days. Falls back to the carrier's when null.
     * @param etaDaysMin Transit time lower bound in calendar days, for the checkout. Falls back to the carrier's when null.
     * @param freeAbove Free shipping at or above this order value — wins over every pricing model, including a matrix. Compared net or gross as the market's free_above_compares setting declares. Null falls back to the tenant's shop-wide free_shipping_threshold.
     * @param labels Localized display names. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     * @param matrixAttribute Attribute name for matrix_basis 'attribute' — the key the rate request's `attributes` map is read at. Free text: the set of attributes is the catalogue's, not this app's.
     * @param matrixBasis The measure a matrix method prices its tiers over: total basket weight (in the market's weight unit), total item count, order value, or 'attribute' — any number the rate request carries under matrix_attribute. Null falls back to the tenant's matrix_basis_default. Ignored unless pricing_type is 'matrix'.
     * @param metadata Free-form jsonb the platform never reads or validates — whatever the merchant or their integration needs to keep beside the row (a customer number with the carrier, an ERP key, a label-printer id). The shape varies BY INTEGRATION, not by anything this app knows, so no key is declared and none is reserved; the example is one plausible instance rather than a schema. A flat map of scalars is the convention, and nothing enforces it.
     * @param name Display name shown in the checkout.
     * @param position Sort order in the checkout (default 0) — a rate answer is returned in this order.
     * @param price The fixed price (default 0), in `currency` — ignored for 'free' and 'matrix'.
     * @param pricingType Pricing model (default 'fixed'): 'fixed' is one price for every basket, 'free' is no price at all, 'matrix' is a tiered price read off this method's rate tiers. Only 'matrix' looks at matrix_basis, quote_above and the tier table.
     * @param quoteAbove Above this MATRIX MEASURE the method carries no automatic price: it is still offered, flagged `quote_required` with a reason, and the storefront shows 'shipping on request'. For bulky or overweight freight priced by hand. Null = every measure is priced automatically.
     * @param taxClass This method's own tax class, as a CODE into the buyer market's tax classes (markets.tax_classes) — never a rate. First step of the tax chain: unset falls back to the tenant's shipping_tax_class setting, then the market default. Not a foreign key and it could not be (ADR-0055); GET /shipping/tax-classes/{code}/usage is the integrity question markets asks in its place.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingMethodsUpdate(
        id: String,
        carrier: String? = null,
        carrierId: String? = null,
        code: String? = null,
        countries: List<String>? = null,
        currency: String? = null,
        description: String? = null,
        enabled: Boolean? = null,
        etaDaysMax: Long? = null,
        etaDaysMin: Long? = null,
        freeAbove: Double? = null,
        labels: Any? = null,
        matrixAttribute: String? = null,
        matrixBasis: com.revenexx.enums.ShippingMethodMatrixBasis? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        price: Double? = null,
        pricingType: com.revenexx.enums.ShippingMethodPricingType? = null,
        quoteAbove: Double? = null,
        taxClass: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "carrier" to carrier,
            "carrier_id" to carrierId,
            "code" to code,
            "countries" to countries,
            "currency" to currency,
            "description" to description,
            "enabled" to enabled,
            "eta_days_max" to etaDaysMax,
            "eta_days_min" to etaDaysMin,
            "free_above" to freeAbove,
            "labels" to labels,
            "matrix_attribute" to matrixAttribute,
            "matrix_basis" to matrixBasis,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "price" to price,
            "pricing_type" to pricingType,
            "quote_above" to quoteAbove,
            "tax_class" to taxClass,
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
     * The rate matrix of one method — every `from_value` threshold with the price charged at or above it — lowest threshold first. Filterable by `?from_value=` — the unique index is (tenant_id, method_id, from_value), so that addresses one row of the matrix by the threshold it prices rather than by an id a bulk replace has already discarded. The applied filters are echoed in `filter`, which always carries the `method_id` taken from the path.
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param limit Page size (default 50, max 200). A value outside the range is clamped rather than refused, and `page.limit` echoes what was applied.
     * @param offset Row offset for pagination (default 0). The next page is `page.offset + page.returned`.
     * @param order Sort as 'column.asc' | 'column.desc' — a bare 'column' sorts ascending. The column must be one this entity has; anything else is a 400 from the data plane.
     * @param fromValue Exact-match filter on `from_value`. The tier at exactly this threshold. (tenant_id, method_id, from_value) is unique, so this addresses one row of the matrix by what it MEANS rather than by an id a bulk replace has already thrown away.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingTiersList(
        methodId: String,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        fromValue: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers"
            .replace("{methodId}", methodId)

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "from_value" to fromValue,
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
     * A rate tier is one row of a matrix method's price table: a `from_value` threshold and the price charged at or above it. The bound is INCLUSIVE and the winning tier is the one with the highest `from_value` at or below the measured value, so a measure of exactly 10 is priced by the tier at 10. What the number measures is the method's `matrix_basis` — kilograms in the market's own weight unit, items, money in the method's currency, or a named attribute — and the last tier has no upper bound. This adds ONE row to the table of the method in the path, leaving the rest alone — the edit for a merchant who has added a heavier bracket. To lay a whole table down at once use PUT …/tiers (set semantics) or POST …/tiers/ladder (evenly stepped), and note that both of those DISCARD the ids of the rows they replace. Two rows of this tenant may not share the combination of `method_id` + `from_value` — that is the 409. `method_id` is taken from the path on every write, so a body naming a different method is ignored rather than obeyed.
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param fromValue Lower bound of this tier, in the method's matrix measure — kilograms (or whatever the market's `weight_unit` names, converted through its factor) for a weight matrix, items for quantity, money in the method's currency for order_value, and the raw attribute value for 'attribute'. INCLUSIVE: the tier applies from this value upward, and the tier that wins is the one with the highest from_value at or below the measured value, so a measure of exactly 10 is priced by the tier at 10 rather than the one below it. The last tier has no upper bound. Unique per method — a second tier at the same threshold is a 409, because which of the two won would be whatever the database returned first. Defaults to 0.
     * @param position Display order in the matrix editor (default 0; a bulk replace derives it from the array index). Pricing reads from_value, never this.
     * @param price What this tier costs, in the method's currency. Charged in full for the whole consignment — a matrix is a lookup table, not a rate per unit. Defaults to 0.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingTiersCreate(
        methodId: String,
        fromValue: Double? = null,
        position: Long? = null,
        price: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers"
            .replace("{methodId}", methodId)

        val apiParams = mutableMapOf<String, Any?>(
            "from_value" to fromValue,
            "position" to position,
            "price" to price,
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
     * The write behind a table editor: a merchant edits the whole matrix on screen and saves it in one call, rather than diffing it into a row added here and a row deleted there. Set semantics, and it replaces EVERY tier the method had: the tiers this method has afterwards are exactly the ones handed in, positions derived from the array order. An empty `tiers` array clears the table — and a matrix method with no tiers quotes nothing, with a reason.
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param tiers The complete new tier set (set semantics) — positions are derived from the array order. An empty array clears the matrix, and a matrix method with no tiers quotes nothing.
     * @return [com.revenexx.models.Error]
     */
    suspend fun shippingTiersReplace(
        methodId: String,
        tiers: List<com.revenexx.models.ShippingRateTierReplaceItem>,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers"
            .replace("{methodId}", methodId)

        val apiParams = mutableMapOf<String, Any?>(
            "tiers" to tiers,
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
     * The tier table a merchant describes in words — "0 to 30 kg, every 5 kg, €4.90 plus €2 a step" — without typing every row. Replaces the method's tiers by default (set replace=false to append).
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param basePrice Price of the first tier.
     * @param step Distance between two tiers. Must be > 0.
     * @param toValue Last tier threshold. The final tier keeps applying above it — a matrix has no upper bound. Must be >= from_value.
     * @param fromValue First tier threshold (default 0), in the method's matrix measure.
     * @param replace Replace the whole table (default true) or append to it.
     * @param stepPrice Added to each subsequent tier (default 0). A negative value is allowed as long as no tier ends up below 0.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingTiersLadder(
        methodId: String,
        basePrice: Double,
        step: Double,
        toValue: Double,
        fromValue: Double? = null,
        replace: Boolean? = null,
        stepPrice: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/ladder"
            .replace("{methodId}", methodId)

        val apiParams = mutableMapOf<String, Any?>(
            "base_price" to basePrice,
            "from_value" to fromValue,
            "replace" to replace,
            "step" to step,
            "step_price" to stepPrice,
            "to_value" to toValue,
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
     * A rate tier is one row of a matrix method's price table: a `from_value` threshold and the price charged at or above it. The bound is INCLUSIVE and the winning tier is the one with the highest `from_value` at or below the measured value, so a measure of exactly 10 is priced by the tier at 10. What the number measures is the method's `matrix_basis` — kilograms in the market's own weight unit, items, money in the method's currency, or a named attribute — and the last tier has no upper bound. Removing a tier in the MIDDLE of a table is harmless — the measures it used to cover fall to the highest remaining threshold below them. Removing the LOWEST one is not: a measure under the new lowest threshold matches no tier at all, and the method is then left out of POST /shipping/rates with 'no tier covers measure …' instead of being quoted at 0, so an entire band of baskets silently stops being offered this method. Deleting the last tier takes the method out of the checkout altogether. Rebuilding the table wholesale is PUT …/tiers or POST …/tiers/ladder; deleting the method deletes its tiers on its own.
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param id The row id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun shippingTiersDelete(
        methodId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/{id}"
            .replace("{methodId}", methodId)
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
     * A rate tier is one row of a matrix method's price table: a `from_value` threshold and the price charged at or above it. The bound is INCLUSIVE and the winning tier is the one with the highest `from_value` at or below the measured value, so a measure of exactly 10 is priced by the tier at 10. What the number measures is the method's `matrix_basis` — kilograms in the market's own weight unit, items, money in the method's currency, or a named attribute — and the last tier has no upper bound. This reads one row of that table by id, under the method that owns it; a tier id belonging to another method is a 404 rather than somebody else's price. A tier id is not durable: PUT …/tiers and POST …/tiers/ladder replace the table by deleting and recreating it, so an id read before either of them names nothing afterwards. Where a caller wants a stable handle, address the row by what it MEANS — GET …/tiers?from_value=… — since (method_id, from_value) is unique.
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param id The row id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun shippingTiersGet(
        methodId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/{id}"
            .replace("{methodId}", methodId)
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
     * A tier id is not stable across a bulk edit: `PUT …/tiers` and `POST …/tiers/ladder` replace the table by deleting and recreating it, so an id read before either of them is gone afterwards.
     *
     * @param methodId The shipping method these tiers belong to. A method this tenant does not have is a 404, never an empty page.
     * @param id The row id.
     * @param fromValue Lower bound of this tier, in the method's matrix measure — kilograms (or whatever the market's `weight_unit` names, converted through its factor) for a weight matrix, items for quantity, money in the method's currency for order_value, and the raw attribute value for 'attribute'. INCLUSIVE: the tier applies from this value upward, and the tier that wins is the one with the highest from_value at or below the measured value, so a measure of exactly 10 is priced by the tier at 10 rather than the one below it. The last tier has no upper bound. Unique per method — a second tier at the same threshold is a 409, because which of the two won would be whatever the database returned first. Defaults to 0.
     * @param position Display order in the matrix editor (default 0; a bulk replace derives it from the array index). Pricing reads from_value, never this.
     * @param price What this tier costs, in the method's currency. Charged in full for the whole consignment — a matrix is a lookup table, not a rate per unit. Defaults to 0.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingTiersUpdate(
        methodId: String,
        id: String,
        fromValue: Double? = null,
        position: Long? = null,
        price: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/{id}"
            .replace("{methodId}", methodId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "from_value" to fromValue,
            "position" to position,
            "price" to price,
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
     * The question a checkout asks, and the only route that answers a PRICE. Hand in the buyer context — the destination country, the order value, and whatever the matrix methods measure: a weight, a quantity or a named product attribute — and this comes back with the methods that may be offered and what each of them costs, free-above thresholds, country restrictions, the carrier's delivery promise and tax already applied. A method that does not apply is never an error: it moves to `excluded` with a reason. So is a tax rate that cannot be resolved — `tax.resolved: false` means the rates are UNKNOWN, not untaxed.
     *
     * @param at The instant to evaluate the delivery estimate at (ISO 8601). Omitted: now. Lets a storefront compute the cut-off in its own timezone.
     * @param attributes Measure values for attribute matrices, keyed by attribute NAME — the key a matrix method names in its matrix_attribute, and the value the number its tiers are matched against. Summed over the basket by the caller, not by this app. Only the key a method asks for is read; anything else in the map is carried along and ignored, and a value that is not a finite number excludes that method with a reason rather than failing the quote.
     * @param country Destination ISO 3166-1 alpha-2 code — compared upper-cased against method and carrier country restrictions. Omitted or null: every method that restricts by country is excluded, with a reason.
     * @param currency ISO 4217 code, echoed into the rates (default 'EUR'). Echoed, not converted: this app prices in the currency the method carries.
     * @param marketId Buyer market for tax resolution. Omitted: the market matching `country`, else the tenant's sole market — never an arbitrary one.
     * @param orderValue Order value (default 0) — drives order_value matrices, and free-above thresholds when no sided value is sent. Read on the basis the tenant's free_above_compares setting declares.
     * @param orderValueGross Order value including tax. Compared against free-above thresholds when free_above_compares is 'gross'.
     * @param orderValueNet Order value excluding tax. Compared against free-above thresholds when free_above_compares is 'net'.
     * @param quantity Total quantity — measure for quantity matrices.
     * @param weight Total weight — measure for weight matrices. Read in weight_unit and converted to the unit the tiers are keyed in.
     * @param weightUnit The unit `weight` is expressed in, as a CODE into the tenant's own weight units (GET /shipping/weight-units). Omitted, it is the unit this market quotes in. A unit the tenant does not keep is a 400 — a mis-read weight prices the wrong bracket silently, and guessing is worse than refusing.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun shippingRates(
        at: String? = null,
        attributes: Any? = null,
        country: String? = null,
        currency: String? = null,
        marketId: String? = null,
        orderValue: Double? = null,
        orderValueGross: Double? = null,
        orderValueNet: Double? = null,
        quantity: Double? = null,
        weight: Double? = null,
        weightUnit: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/shipping/rates"

        val apiParams = mutableMapOf<String, Any?>(
            "at" to at,
            "attributes" to attributes,
            "country" to country,
            "currency" to currency,
            "market_id" to marketId,
            "order_value" to orderValue,
            "order_value_gross" to orderValueGross,
            "order_value_net" to orderValueNet,
            "quantity" to quantity,
            "weight" to weight,
            "weight_unit" to weightUnit,
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
     * markets.tax_classes is the source of record for the rate and this app points at it by CODE from two places: a method's own tax_class and the tenant's shipping_tax_class fallback. Neither is a foreign key and neither could be — a cross-app FK is what ADR-0055 forbids — so integrity is a question one app asks the other, and this is the answering half. It is asked before a destructive edit: markets calls it when an operator tries to delete a tax class, and a count above zero is what stops the delete rather than leaving these methods pointing at a code nobody serves. Matched as a CODE, not a row: a tax class is unique per market, so 'reduced' may exist in several and a method naming it does not say which one it meant. Reports at most 500 methods and names the first 20. Every code answers, used or not — a code nobody points at is `in_use: false`, never a 404.
     *
     * @param code The tax-class CODE, as markets spells it — not a row id. Matched against every shipping method's `tax_class` and against this market's `shipping_tax_class` setting.
     * @return [com.revenexx.models.ShippingTaxClassUsage]
     */
    suspend fun shippingTaxClassesUsage(
        code: String,
    ): com.revenexx.models.ShippingTaxClassUsage {
        val apiPath = "/v1/shipping/tax-classes/{code}/usage"
            .replace("{code}", code)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ShippingTaxClassUsage = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingTaxClassUsage.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingTaxClassUsage::class.java,
            converter,
        )
    }


}