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
 * Commerce Studio Markets App — the market/region backbone of the Revenue Cloud. A market is a distinct business context within a tenant (a country, a region, a B2C storefront segment) with its own base currency, locales (language + country), traded currencies and tax classes (standard, reduced, …). Markets provides the 'market' scope dimension to the Entity Scoping Engine, so every other commerce app (products, orders, customers, …) can slice its data per market. Storefronts resolve their full market context (currency, locales, currencies, tax classes) in one call.
 */
class Markets(client: Client) : Service(client) {

    /**
     * Every column is an exact-match filter and they combine with AND (?code=northwind); each one is declared as a query parameter above. A `?column=value` this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list — and `filter` echoes what was actually applied, which is the only way to tell that apart from a filter that matched nothing.
     *
     * @param id Exact match on `id`. Primary key. Note that OTHER apps do not store this: the market scope dimension is keyed on `code` (manifest `provides_scopes.slug_source = markets.code`), so a row elsewhere that is "in this market" carries the code, not this uuid. It is the item routes and /context that want this value.
     * @param code Exact match on `code`. Market code, unique per tenant, and the single most load-bearing string in this app: it IS the market scope slug. The Entity Scoping Engine publishes it as the `market` dimension (`scope_context.market` in the JWT), and every other commerce app — products, prices, orders, customers — stores THIS value to say which market a row belongs to. Renaming it re-keys that scope for everyone, so treat it as permanent. Accepted in place of the uuid on /readiness, /clone, /backfill and /make-default — but not on the item routes or /context, which take a uuid only.
     * @param name Exact match on `name`. Display name, in the operator's own language. Cockpit copy only — nothing resolves a market by it.
     * @param labels Exact match on `labels`. Exact whole-document equality on the jsonb: the value is a whole JSON document and has to match every key, so this is not a path or a containment query. Key order and whitespace are irrelevant — the comparison is semantic. A value that does not parse as JSON is refused with 400 `invalid_value` rather than answered with zero rows. Localized display names for storefronts, keyed by locale: a flat {locale: label} map, one level deep, string values. WHICH key to write is not free — GET /markets/{id}/context returns `locale_policy`, whose `write` is the key this tenant keys by (a full locale under regional granularity, a bare language under language granularity) and whose `read` is the order to try. Null means nothing is translated and `name` is all there is.
     * @param currency Exact match on `currency`. Base currency this market quotes in — ISO 4217, and schema.json's own default is 'EUR'. This is the single currency prices are STATED in; the currencies collection under the market is the wider set it accepts. A base currency missing from that collection is a blocking readiness failure.
     * @param status Exact match on `status`. Default 'active'. Only an active market serves a storefront; 'inactive' keeps the market and all its configuration but takes it out of service. Readiness reports an active market that cannot trade as `serving: true, ready: false` — live and broken.
     * @param isDefault Exact match on `is_default`. The tenant default market — what a call naming no market falls back to. Exactly one market holds it; move it with POST /markets/{id}/make-default rather than by writing this flag, which does not demote the market that currently holds it.
     * @param position Exact match on `position`. Sort position among the tenant's markets, ascending, default 0. Presentation only — it decides the order the Cockpit and a market picker list them in, and nothing resolves a market by it.
     * @param createdAt Exact match on `created_at`. When the market row was inserted. Set by the database; never writable.
     * @param updatedAt Exact match on `updated_at`. When the market row was last written. Set by the database on every update; never writable.
     * @param limit Page size (default 50, max 200). Out of range is CLAMPED, not refused — ?limit=999 answers 200 with 200 rows, and `page.limit` says so.
     * @param offset Row offset for pagination (default 0). A negative offset is clamped to 0 rather than refused.
     * @param order Sort as 'column' | 'column.asc' | 'column.desc'. The direction is lower case, and the column has to exist: id, code, name, labels, currency, status, is_default, position, created_at, updated_at.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsList(
        id: String? = null,
        code: String? = null,
        name: String? = null,
        labels: String? = null,
        currency: String? = null,
        status: com.revenexx.enums.MarketsListStatus? = null,
        isDefault: Boolean? = null,
        position: Long? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "code" to code,
            "name" to name,
            "labels" to labels,
            "currency" to currency,
            "status" to status,
            "is_default" to isDefault,
            "position" to position,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
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
     * A market needs a 'code' and a 'name' — currency defaults to EUR, status to active. To get a market that can actually trade, clone an existing one instead: POST /markets/{id}/clone.
     *
     * @param code Market code, unique per tenant, and the single most load-bearing string in this app: it IS the market scope slug. The Entity Scoping Engine publishes it as the `market` dimension (`scope_context.market` in the JWT), and every other commerce app — products, prices, orders, customers — stores THIS value to say which market a row belongs to. Renaming it re-keys that scope for everyone, so treat it as permanent. Accepted in place of the uuid on /readiness, /clone, /backfill and /make-default — but not on the item routes or /context, which take a uuid only.
     * @param name Display name, in the operator's own language. Cockpit copy only — nothing resolves a market by it.
     * @param currency Base currency this market quotes in — ISO 4217, and schema.json's own default is 'EUR'. This is the single currency prices are STATED in; the currencies collection under the market is the wider set it accepts. A base currency missing from that collection is a blocking readiness failure.
     * @param isDefault The tenant default market — what a call naming no market falls back to. Exactly one market holds it; move it with POST /markets/{id}/make-default rather than by writing this flag, which does not demote the market that currently holds it.
     * @param labels Localized display names for storefronts, keyed by locale: a flat {locale: label} map, one level deep, string values. WHICH key to write is not free — GET /markets/{id}/context returns `locale_policy`, whose `write` is the key this tenant keys by (a full locale under regional granularity, a bare language under language granularity) and whose `read` is the order to try. Null means nothing is translated and `name` is all there is.
     * @param position Sort position among the tenant's markets, ascending, default 0. Presentation only — it decides the order the Cockpit and a market picker list them in, and nothing resolves a market by it.
     * @param status Default 'active'. Only an active market serves a storefront; 'inactive' keeps the market and all its configuration but takes it out of service. Readiness reports an active market that cannot trade as `serving: true, ready: false` — live and broken.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsCreate(
        code: String,
        name: String,
        currency: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        status: com.revenexx.enums.MarketStatus? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "currency" to currency,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "status" to status,
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
     * How this tenant keys its translations, resolved for a surface that stands in no market at all. The Cockpit edits a tenant BASELINE when no market is selected, and a baseline value has to be readable by every market — so the locale set answered here is the UNION of every market's locales, each one already resolved to the key it is written under, not one market's list and not a pair of setting names to re-implement. Each entry names the markets that asked for that locale: an editor listing six inputs without saying who needs them invites translations nobody will ever read. Write/read keys follow the same two settings as the per-market answer, so a baseline and a market value can never be keyed differently.
     *
     * @return [com.revenexx.models.TenantLocalePolicy]
     */
    suspend fun marketsLocalePolicy(
    ): com.revenexx.models.TenantLocalePolicy {
        val apiPath = "/v1/markets/locale-policy"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.TenantLocalePolicy = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.TenantLocalePolicy.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.TenantLocalePolicy::class.java,
            converter,
        )
    }


    /**
     * Every closed value set this app owns, listed by name with its title and its description but WITHOUT its values — enough to build a menu of them, and a name to fetch one by when a select box actually needs the values. Static per app version; nothing about a tenant changes it. It reads no table and takes no parameter, so 200 is the only answer it has beyond the gateway's own.
     *
     * @return [com.revenexx.models.MarketsVocabularyIndex]
     */
    suspend fun marketsVocabularies(
    ): com.revenexx.models.MarketsVocabularyIndex {
        val apiPath = "/v1/markets/vocabularies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MarketsVocabularyIndex = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MarketsVocabularyIndex.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MarketsVocabularyIndex::class.java,
            converter,
        )
    }


    /**
     * One value set in full: every value the column may hold, in the order it may hold them, with the copy and the badge tone a client renders each one as. The values are not kept in a list beside the database, they are parsed out of the CHECK constraint in this app's own schema.json — so the set served here IS the set enforced on a write, and a select box built from it cannot offer a value the write would then refuse. A name outside the declared enum is a 404 rather than an empty list — an empty vocabulary and an unknown one mean different things to a select box.
     *
     * @param name Which vocabulary to read. The enum is exhaustive — these are every value set this app owns, and anything else is a 404.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsVocabulary(
        name: com.revenexx.enums.MarketsVocabularyName,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/vocabularies/{name}"
            .replace("{name}", name.value)

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
     * Deleting a market takes its locales, currencies and tax classes with it: all three carry an ON DELETE CASCADE onto markets.id, so this is never refused for having children.
     *
     * @param id The market, by its primary key. A uuid — this route does not resolve a market code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}"
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
     * Resolved by uuid only — unlike /readiness, /clone, /backfill and /make-default, a market CODE here is a 400 rather than a lookup.
     *
     * @param id The market, by its primary key. A uuid — this route does not resolve a market code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}"
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
     * Partial: omitted fields keep their value.
     *
     * @param id The market, by its primary key. A uuid — this route does not resolve a market code, so a segment that will not cast is a 400 before any row is read.
     * @param code Market code, unique per tenant, and the single most load-bearing string in this app: it IS the market scope slug. The Entity Scoping Engine publishes it as the `market` dimension (`scope_context.market` in the JWT), and every other commerce app — products, prices, orders, customers — stores THIS value to say which market a row belongs to. Renaming it re-keys that scope for everyone, so treat it as permanent. Accepted in place of the uuid on /readiness, /clone, /backfill and /make-default — but not on the item routes or /context, which take a uuid only.
     * @param currency Base currency this market quotes in — ISO 4217, and schema.json's own default is 'EUR'. This is the single currency prices are STATED in; the currencies collection under the market is the wider set it accepts. A base currency missing from that collection is a blocking readiness failure.
     * @param isDefault The tenant default market — what a call naming no market falls back to. Exactly one market holds it; move it with POST /markets/{id}/make-default rather than by writing this flag, which does not demote the market that currently holds it.
     * @param labels Localized display names for storefronts, keyed by locale: a flat {locale: label} map, one level deep, string values. WHICH key to write is not free — GET /markets/{id}/context returns `locale_policy`, whose `write` is the key this tenant keys by (a full locale under regional granularity, a bare language under language granularity) and whose `read` is the order to try. Null means nothing is translated and `name` is all there is.
     * @param name Display name, in the operator's own language. Cockpit copy only — nothing resolves a market by it.
     * @param position Sort position among the tenant's markets, ascending, default 0. Presentation only — it decides the order the Cockpit and a market picker list them in, and nothing resolves a market by it.
     * @param status Default 'active'. Only an active market serves a storefront; 'inactive' keeps the market and all its configuration but takes it out of service. Readiness reports an active market that cannot trade as `serving: true, ready: false` — live and broken.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsUpdate(
        id: String,
        code: String? = null,
        currency: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        name: String? = null,
        position: Long? = null,
        status: com.revenexx.enums.MarketStatus? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "currency" to currency,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "status" to status,
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
     * Repairs the market in the path out of a source market that is already right. The two are compared by CODE, collection by collection, and only the codes this market does not already carry are added — so a locale, a currency or a tax class it already holds is left exactly as the merchant left it, rate included, and is never overwritten. Both the path id and `source` are resolved by uuid OR by market code. Idempotent: running it twice adds nothing the second time.
     *
     * @param id The market being REPAIRED — a uuid or a market code.
     * @param source The market to copy the missing pieces FROM — a uuid or a market code. Must not be the market in the path. Pick a market that is already right; nothing about it is changed.
     * @param currencies Take the source's traded currencies for codes this market does not already carry. Default true.
     * @param locales Take the source's locales for codes this market does not already carry. Default true.
     * @param taxClasses Take the source's tax classes for codes this market does not already carry. An existing code keeps ITS rate — a backfill never re-rates a class the merchant already set. Default true.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsBackfill(
        id: String,
        source: String,
        currencies: Boolean? = null,
        locales: Boolean? = null,
        taxClasses: Boolean? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}/backfill"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "currencies" to currencies,
            "locales" to locales,
            "source" to source,
            "tax_classes" to taxClasses,
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
     * Creates a NEW market out of an existing one, taking its locales, its traded currencies and its tax classes with it in a single call. That is the difference between this and POST /markets: a plain create leaves a row that cannot serve anybody, while what comes back here is a market with a language to render in, a currency to price in and a rate to tax with. The path id is the SOURCE market, resolved by uuid OR by market code.
     *
     * @param id The SOURCE market to copy — a uuid or a market code.
     * @param code Code of the NEW market (unique per tenant).
     * @param copyCurrencies Copy the source's traded currencies. Default true. The new market's own base currency is registered and marked default either way.
     * @param copyLocales Copy the source's locales. Default true. False leaves the new market with no language of its own, so the tenant fallback_locale is seeded instead — it is never left with none.
     * @param copyTaxClasses Copy the source's tax classes, rates and all. Default true. False leaves the market unable to tax anything, which readiness reports as blocking.
     * @param currency Base currency of the new market (ISO 4217). Defaults to the source market's, and is registered and marked default on the new one either way.
     * @param name Display name of the new market. Defaults to its code.
     * @param status Status of the new market. Defaults to 'active'; clone it 'inactive' to build it out before it serves anyone.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsClone(
        id: String,
        code: String,
        copyCurrencies: Boolean? = null,
        copyLocales: Boolean? = null,
        copyTaxClasses: Boolean? = null,
        currency: String? = null,
        name: String? = null,
        status: com.revenexx.enums.MarketStatus? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}/clone"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "copy_currencies" to copyCurrencies,
            "copy_locales" to copyLocales,
            "copy_tax_classes" to copyTaxClasses,
            "currency" to currency,
            "name" to name,
            "status" to status,
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
     * The storefront bootstrap: everything a frontend needs to render one market, resolved server-side so no client re-derives it — the market row, its locales, the currencies it trades in and its tax classes; WHICH locale to actually render in and where that answer came from; which key to read and write a translation under; whether the prices it will be handed are gross or net; and whether any of it is trustworthy. One call rather than five, and — more to the point — one place the resolution rules live, instead of a slightly different copy of them in every storefront. This one resolves the market by id only: unlike /readiness, /clone and /backfill, a market CODE here is a 400, not a lookup.
     *
     * @param id The market. A uuid — this route does not accept a market code.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsContext(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}/context"
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
     * A tenant has ONE default market: it is what every call naming none falls back to. Moving the flag from a client was promote-then-demote, two PATCHes that leave two defaults when the second does not land and none when the first does. This is the one call instead — it promotes the market in the path and demotes whoever held the flag in the same operation, writing once per row that was actually wrong and not touching the rest. Accepts an id or a market CODE. Answers the market plus the codes it demoted; repeating the call writes nothing.
     *
     * @param id The market to promote — a uuid or a market code.
     * @param data Request body
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsMakeDefault(
        id: String,
        data: Any,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}/make-default"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
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
     * Whether this market can actually trade, and if not, what is missing. Every check runs on every call and comes back with its own severity, so the answer is a diagnosis rather than a yes or a no: a market with no currency registered has nothing to price in and a market with no tax class has nothing to tax with, and both of those fail BLOCKING, which is what turns `ready` false. A check that is merely degraded — no locale of its own, while the tenant declares a fallback_locale that covers for it — fails as a warning and leaves the market serviceable. Resolves the market by uuid OR by market code.
     *
     * @param id The market — a uuid or a market code.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsReadiness(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{id}/readiness"
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
     * Every column is an exact-match filter and they combine with AND (?code=EUR); each one is declared as a query parameter above. A `?column=value` this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list — and `filter` echoes what was actually applied, which is the only way to tell that apart from a filter that matched nothing. `market_id` is not among them: the owning market comes from the path and overwrites anything the query says. An unknown but well-formed market lists empty rather than 404 — the parent is filtered on, not verified.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id Exact match on `id`. Primary key of this currency registration. The currency is named by `code` everywhere else.
     * @param code Exact match on `code`. ISO 4217 code, unique per market — one entry in the set of currencies this market TRADES in, as opposed to the single base currency on the market row that its prices are quoted in. The base currency must appear here or the market cannot serve; clone and backfill register it for you.
     * @param isDefault Exact match on `is_default`. The currency offered first to a buyer who states no preference. At most one per market, and it should be the market's base currency — readiness reports it as a warning when it is not.
     * @param position Exact match on `position`. Sort position among this market's currencies, ascending, default 0 — the order a currency switcher lists them in.
     * @param createdAt Exact match on `created_at`. When the currency was registered on this market. Set by the database; never writable.
     * @param limit Page size (default 50, max 200). Out of range is CLAMPED, not refused — ?limit=999 answers 200 with 200 rows, and `page.limit` says so.
     * @param offset Row offset for pagination (default 0). A negative offset is clamped to 0 rather than refused.
     * @param order Sort as 'column' | 'column.asc' | 'column.desc'. The direction is lower case, and the column has to exist: id, market_id, code, is_default, position, created_at.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsCurrenciesList(
        marketId: String,
        id: String? = null,
        code: String? = null,
        isDefault: Boolean? = null,
        position: Long? = null,
        createdAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/currencies"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "code" to code,
            "is_default" to isDefault,
            "position" to position,
            "created_at" to createdAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
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
     * The owning market comes from the path and overrides anything in the body.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param code ISO 4217 code, unique per market — one entry in the set of currencies this market TRADES in, as opposed to the single base currency on the market row that its prices are quoted in. The base currency must appear here or the market cannot serve; clone and backfill register it for you.
     * @param isDefault The currency offered first to a buyer who states no preference. At most one per market, and it should be the market's base currency — readiness reports it as a warning when it is not.
     * @param position Sort position among this market's currencies, ascending, default 0 — the order a currency switcher lists them in.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsCurrenciesCreate(
        marketId: String,
        code: String,
        isDefault: Boolean? = null,
        position: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/currencies"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "position" to position,
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
     * Scoped to the market in the path — a row belonging to another market is a 404 here, and is never deleted.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The currency of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsCurrenciesDelete(
        marketId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/currencies/{id}"
            .replace("{marketId}", marketId)
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
     * Scoped strictly to the market in the path: a row belonging to another market is a 404 here, never a 200.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The currency of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsCurrenciesGet(
        marketId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/currencies/{id}"
            .replace("{marketId}", marketId)
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
     * Partial: omitted fields keep their value.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The currency of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @param code ISO 4217 code, unique per market — one entry in the set of currencies this market TRADES in, as opposed to the single base currency on the market row that its prices are quoted in. The base currency must appear here or the market cannot serve; clone and backfill register it for you.
     * @param isDefault The currency offered first to a buyer who states no preference. At most one per market, and it should be the market's base currency — readiness reports it as a warning when it is not.
     * @param position Sort position among this market's currencies, ascending, default 0 — the order a currency switcher lists them in.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsCurrenciesUpdate(
        marketId: String,
        id: String,
        code: String? = null,
        isDefault: Boolean? = null,
        position: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/currencies/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "position" to position,
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
     * Every column is an exact-match filter and they combine with AND (?code=de-DE); each one is declared as a query parameter above. A `?column=value` this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list — and `filter` echoes what was actually applied, which is the only way to tell that apart from a filter that matched nothing. `market_id` is not among them: the owning market comes from the path and overwrites anything the query says. An unknown but well-formed market lists empty rather than 404 — the parent is filtered on, not verified.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id Exact match on `id`. Primary key of this locale registration. The locale is named by `code` everywhere else.
     * @param code Exact match on `code`. Locale code, language-COUNTRY — the language a storefront renders this market in, and the key a translation is stored under. Unique per market. The app's own seeded value is the tenant's `fallback_locale` setting, whose declared default is de-DE.
     * @param language Exact match on `language`. ISO 639-1 language code — the language half of `code`, stored separately so a client can group markets by language without parsing.
     * @param country Exact match on `country`. ISO 3166-1 alpha-2 country code — the region half of `code`. It is a spelling of the language, not a shipping destination: a market may register de-AT without trading in Austria.
     * @param isDefault Exact match on `is_default`. The locale a storefront renders this market in when the request asks for none. At most one per market; where none carries the flag the first by position is used, and `default_locale.source` on the context says which of the two happened.
     * @param position Exact match on `position`. Sort position among this market's locales, ascending, default 0 — and the tie-break that picks a default when no locale is flagged.
     * @param createdAt Exact match on `created_at`. When the locale was registered on this market. Set by the database; never writable.
     * @param limit Page size (default 50, max 200). Out of range is CLAMPED, not refused — ?limit=999 answers 200 with 200 rows, and `page.limit` says so.
     * @param offset Row offset for pagination (default 0). A negative offset is clamped to 0 rather than refused.
     * @param order Sort as 'column' | 'column.asc' | 'column.desc'. The direction is lower case, and the column has to exist: id, market_id, code, language, country, is_default, position, created_at.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsLocalesList(
        marketId: String,
        id: String? = null,
        code: String? = null,
        language: String? = null,
        country: String? = null,
        isDefault: Boolean? = null,
        position: Long? = null,
        createdAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/locales"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "code" to code,
            "language" to language,
            "country" to country,
            "is_default" to isDefault,
            "position" to position,
            "created_at" to createdAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
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
     * The owning market comes from the path and overrides anything in the body.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param code Locale code, language-COUNTRY — the language a storefront renders this market in, and the key a translation is stored under. Unique per market. The app's own seeded value is the tenant's `fallback_locale` setting, whose declared default is de-DE.
     * @param country ISO 3166-1 alpha-2 country code — the region half of `code`. It is a spelling of the language, not a shipping destination: a market may register de-AT without trading in Austria.
     * @param language ISO 639-1 language code — the language half of `code`, stored separately so a client can group markets by language without parsing.
     * @param isDefault The locale a storefront renders this market in when the request asks for none. At most one per market; where none carries the flag the first by position is used, and `default_locale.source` on the context says which of the two happened.
     * @param position Sort position among this market's locales, ascending, default 0 — and the tie-break that picks a default when no locale is flagged.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsLocalesCreate(
        marketId: String,
        code: String,
        country: String,
        language: String,
        isDefault: Boolean? = null,
        position: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/locales"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "country" to country,
            "is_default" to isDefault,
            "language" to language,
            "position" to position,
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
     * Scoped to the market in the path — a row belonging to another market is a 404 here, and is never deleted.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The locale of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsLocalesDelete(
        marketId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/locales/{id}"
            .replace("{marketId}", marketId)
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
     * Scoped strictly to the market in the path: a row belonging to another market is a 404 here, never a 200.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The locale of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsLocalesGet(
        marketId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/locales/{id}"
            .replace("{marketId}", marketId)
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
     * Partial: omitted fields keep their value.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The locale of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @param code Locale code, language-COUNTRY — the language a storefront renders this market in, and the key a translation is stored under. Unique per market. The app's own seeded value is the tenant's `fallback_locale` setting, whose declared default is de-DE.
     * @param country ISO 3166-1 alpha-2 country code — the region half of `code`. It is a spelling of the language, not a shipping destination: a market may register de-AT without trading in Austria.
     * @param isDefault The locale a storefront renders this market in when the request asks for none. At most one per market; where none carries the flag the first by position is used, and `default_locale.source` on the context says which of the two happened.
     * @param language ISO 639-1 language code — the language half of `code`, stored separately so a client can group markets by language without parsing.
     * @param position Sort position among this market's locales, ascending, default 0 — and the tie-break that picks a default when no locale is flagged.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsLocalesUpdate(
        marketId: String,
        id: String,
        code: String? = null,
        country: String? = null,
        isDefault: Boolean? = null,
        language: String? = null,
        position: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/locales/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "country" to country,
            "is_default" to isDefault,
            "language" to language,
            "position" to position,
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
     * Every column is an exact-match filter and they combine with AND (?code=standard); each one is declared as a query parameter above. A `?column=value` this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list — and `filter` echoes what was actually applied, which is the only way to tell that apart from a filter that matched nothing. `market_id` is not among them: the owning market comes from the path and overwrites anything the query says. An unknown but well-formed market lists empty rather than 404 — the parent is filtered on, not verified.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id Exact match on `id`. Primary key of this tax class. The class is named by `code` everywhere else, including by other apps.
     * @param code Exact match on `code`. Tax class code, unique per market — the rate bucket a product or a shipping method is assigned to ('standard', 'reduced', 'zero'). Other apps name a class by THIS and by nothing else: there is no foreign key behind it and there cannot be (ADR-0055), which is why the delete route asks the shipping app what still points at the code before removing it.
     * @param name Exact match on `name`. Display name of the rate bucket, in the operator's own language.
     * @param labels Exact match on `labels`. Exact whole-document equality on the jsonb: the value is a whole JSON document and has to match every key, so this is not a path or a containment query. Key order and whitespace are irrelevant — the comparison is semantic. A value that does not parse as JSON is refused with 400 `invalid_value` rather than answered with zero rows. Localized display names for storefronts and invoices, keyed by locale: a flat {locale: label} map, one level deep, string values. The key to write is the `locale_policy.write` from GET /markets/{id}/context, exactly as for a market's labels. Null means nothing is translated and `name` is all there is.
     * @param rate Exact match on `rate`. Tax rate in PERCENT, 0–100 (default 0) — 20 means 20 %, not 0.2. Whether a stored price already contains it is a separate question, answered per market by `pricing.tax_basis` on the context.
     * @param isDefault Exact match on `is_default`. The class applied to a line that names none. At most one per market. A market that stores GROSS prices and marks no default cannot break those prices back down into net, which is why readiness turns that combination from a warning into a blocking failure.
     * @param position Exact match on `position`. Sort position among this market's tax classes, ascending, default 0 — and the tie-break that picks a class when none is flagged default.
     * @param createdAt Exact match on `created_at`. When the tax class was created on this market. Set by the database; never writable.
     * @param updatedAt Exact match on `updated_at`. When the tax class was last written. Set by the database on every update; never writable.
     * @param limit Page size (default 50, max 200). Out of range is CLAMPED, not refused — ?limit=999 answers 200 with 200 rows, and `page.limit` says so.
     * @param offset Row offset for pagination (default 0). A negative offset is clamped to 0 rather than refused.
     * @param order Sort as 'column' | 'column.asc' | 'column.desc'. The direction is lower case, and the column has to exist: id, market_id, code, name, labels, rate, is_default, position, created_at, updated_at.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsTaxClassesList(
        marketId: String,
        id: String? = null,
        code: String? = null,
        name: String? = null,
        labels: String? = null,
        rate: Double? = null,
        isDefault: Boolean? = null,
        position: Long? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/tax_classes"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "code" to code,
            "name" to name,
            "labels" to labels,
            "rate" to rate,
            "is_default" to isDefault,
            "position" to position,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
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
     * The owning market comes from the path and overrides anything in the body.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param code Tax class code, unique per market — the rate bucket a product or a shipping method is assigned to ('standard', 'reduced', 'zero'). Other apps name a class by THIS and by nothing else: there is no foreign key behind it and there cannot be (ADR-0055), which is why the delete route asks the shipping app what still points at the code before removing it.
     * @param name Display name of the rate bucket, in the operator's own language.
     * @param isDefault The class applied to a line that names none. At most one per market. A market that stores GROSS prices and marks no default cannot break those prices back down into net, which is why readiness turns that combination from a warning into a blocking failure.
     * @param labels Localized display names for storefronts and invoices, keyed by locale: a flat {locale: label} map, one level deep, string values. The key to write is the `locale_policy.write` from GET /markets/{id}/context, exactly as for a market's labels. Null means nothing is translated and `name` is all there is.
     * @param position Sort position among this market's tax classes, ascending, default 0 — and the tie-break that picks a class when none is flagged default.
     * @param rate Tax rate in PERCENT, 0–100 (default 0) — 20 means 20 %, not 0.2. Whether a stored price already contains it is a separate question, answered per market by `pricing.tax_basis` on the context.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsTaxClassesCreate(
        marketId: String,
        code: String,
        name: String,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        rate: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/tax_classes"
            .replace("{marketId}", marketId)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "rate" to rate,
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
     * Refused with a 409 for as long as another app still points at this tax class by its code. A tax class is the source of record for a rate, and other apps name it by CODE with no foreign key behind it — a cross-app FK is what ADR-0055 forbids. So this asks the shipping app what still uses the code (shipping.tax-classes.usage) and answers 409 with the count and the first few names rather than leaving methods quoting a rate nobody defines. The check FAILS OPEN: a tenant without the shipping app, or an unreachable one, deletes as before, and the answer says which happened in 'usage_checked'. Matched on the code, which is shared across markets — the refusal message says so.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The tax class of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsTaxClassesDelete(
        marketId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/tax_classes/{id}"
            .replace("{marketId}", marketId)
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
     * Scoped strictly to the market in the path: a row belonging to another market is a 404 here, never a 200.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The tax class of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun marketsTaxClassesGet(
        marketId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/tax_classes/{id}"
            .replace("{marketId}", marketId)
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
     * Partial: omitted fields keep their value.
     *
     * @param marketId The owning market. A uuid — this route does not accept a market code. An unknown market lists empty rather than 404.
     * @param id The tax class of a market, by its primary key. A uuid — this route does not resolve a code, so a segment that will not cast is a 400 before any row is read.
     * @param code Tax class code, unique per market — the rate bucket a product or a shipping method is assigned to ('standard', 'reduced', 'zero'). Other apps name a class by THIS and by nothing else: there is no foreign key behind it and there cannot be (ADR-0055), which is why the delete route asks the shipping app what still points at the code before removing it.
     * @param isDefault The class applied to a line that names none. At most one per market. A market that stores GROSS prices and marks no default cannot break those prices back down into net, which is why readiness turns that combination from a warning into a blocking failure.
     * @param labels Localized display names for storefronts and invoices, keyed by locale: a flat {locale: label} map, one level deep, string values. The key to write is the `locale_policy.write` from GET /markets/{id}/context, exactly as for a market's labels. Null means nothing is translated and `name` is all there is.
     * @param name Display name of the rate bucket, in the operator's own language.
     * @param position Sort position among this market's tax classes, ascending, default 0 — and the tie-break that picks a class when none is flagged default.
     * @param rate Tax rate in PERCENT, 0–100 (default 0) — 20 means 20 %, not 0.2. Whether a stored price already contains it is a separate question, answered per market by `pricing.tax_basis` on the context.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun marketsTaxClassesUpdate(
        marketId: String,
        id: String,
        code: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        name: String? = null,
        position: Long? = null,
        rate: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/markets/{market_id}/tax_classes/{id}"
            .replace("{marketId}", marketId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "rate" to rate,
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