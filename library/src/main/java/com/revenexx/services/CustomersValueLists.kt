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
 * The value sets a merchant owns, and the fixed ones they do not. Payment terms, address types, lifecycle stages and activity types were CHECK constraints until a wholesaler wanted net 45 and a pipeline step of their own — they are the tenant's ROWS now, so adding one is a call rather than a release of this app. Alongside them the vocabularies: the enums this app really does fix (status, registration status, membership source), published with the titles, descriptions and badge tones a client needs to render a value it has never seen. Plus the one call that seeds a fresh tenant with all four sets.
 */
class CustomersValueLists(client: Client) : Service(client) {

    /**
     * What an address is used for. Billing and shipping are what a checkout needs; a works entrance or a central accounts office is the tenant's own. A fresh install is seeded with billing, shipping, and the set seeds on first read too, so the page is never empty and `addresses.type` always has a value it may carry. The whole set comes back in one page in the tenant's own order — this route takes no limit/offset/order and no column filters, so `page` describes the full set and `filter` is always empty.
     *
     * @return [Any]
     */
    suspend fun customersAddressTypesList(
    ): Any {
        val apiPath = "/v1/customers/address-types"

        val apiParams = mutableMapOf<String, Any?>(
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
     * Extends this tenant's address types set with a value of their own — the whole reason these four stopped being CHECK constraints. What an address is used for. Billing and shipping are what a checkout needs; a works entrance or a central accounts office is the tenant's own. The code is lowercase and becomes what `addresses.type` stores; it cannot be changed afterwards, because every record carrying it would be orphaned.
     *
     * @param code What `addresses.type` will store. Lowercase, starting with a letter; immutable afterwards.
     * @param title The fallback name shown when no locale matches.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted in the same call.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending. Default 0.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAddressTypesCreate(
        code: String,
        title: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/address-types"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * Takes a value out of the address types set. There is no foreign key behind `addresses.type` — one added to a table that starts empty fails the migration of every existing tenant — so this route IS the integrity: it refuses while any record still carries the code, and it refuses to empty the set. Retiring a value that is in use is therefore a two-step job: move the records onto another value first, then remove it.
     *
     * @param id The address type to remove.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAddressTypesDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/address-types/{id}"
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
     * One value of the address types set, by its id — its code, its fallback title, the per-language `labels` an operator reads and the badge `tone` a client renders it with. What an address is used for. Billing and shipping are what a checkout needs; a works entrance or a central accounts office is the tenant's own. Reading one value is the rare path: `GET /customers/address-types` answers the whole set in a single page, which is what a select needs.
     *
     * @param id The address type to read. Note that records store the CODE, not this id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersAddressTypesGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/address-types/{id}"
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
     * Everything about a value except the value itself: its titles, its help text, its badge tone, its `position` in the select, and which one of the set is the default. The `code` is immutable, so no record carrying it is ever orphaned by an edit here — a merchant who retitles `shipping` to wording of their own changes what people READ and nothing about what `addresses.type` stores. Seeded values (`is_system`) are renameable like any other, and re-seeding leaves the rename alone.
     *
     * @param id The address type to edit.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending.
     * @param title The fallback name shown when no locale matches.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersAddressTypesUpdate(
        id: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        title: String? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/address-types/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * What kind of entry lands on a customer timeline. 'system' is the app's own decision trail and a caller may not file one, whatever the set says. A fresh install is seeded with system, note, call, email, meeting, visit, task, and the set seeds on first read too, so the page is never empty and `contact_events.kind` always has a value it may carry. The whole set comes back in one page in the tenant's own order — this route takes no limit/offset/order and no column filters, so `page` describes the full set and `filter` is always empty.
     *
     * @return [Any]
     */
    suspend fun customersContactEventKindsList(
    ): Any {
        val apiPath = "/v1/customers/contact-event-kinds"

        val apiParams = mutableMapOf<String, Any?>(
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
     * Extends this tenant's activity types set with a value of their own — the whole reason these four stopped being CHECK constraints. What kind of entry lands on a customer timeline. 'system' is the app's own decision trail and a caller may not file one, whatever the set says. The code is lowercase and becomes what `contact_events.kind` stores; it cannot be changed afterwards, because every record carrying it would be orphaned.
     *
     * @param code What `contact_events.kind` will store. Lowercase, starting with a letter; immutable afterwards.
     * @param title The fallback name shown when no locale matches.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted in the same call.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending. Default 0.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersContactEventKindsCreate(
        code: String,
        title: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contact-event-kinds"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * Takes a value out of the activity types set. There is no foreign key behind `contact_events.kind` — one added to a table that starts empty fails the migration of every existing tenant — so this route IS the integrity: it refuses while any record still carries the code, and it refuses to empty the set. Retiring a value that is in use is therefore a two-step job: move the records onto another value first, then remove it.
     *
     * @param id The activity type to remove.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersContactEventKindsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contact-event-kinds/{id}"
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
     * One value of the activity types set, by its id — its code, its fallback title, the per-language `labels` an operator reads and the badge `tone` a client renders it with. What kind of entry lands on a customer timeline. 'system' is the app's own decision trail and a caller may not file one, whatever the set says. Reading one value is the rare path: `GET /customers/contact-event-kinds` answers the whole set in a single page, which is what a select needs.
     *
     * @param id The activity type to read. Note that records store the CODE, not this id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersContactEventKindsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contact-event-kinds/{id}"
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
     * Everything about a value except the value itself: its titles, its help text, its badge tone, its `position` in the select, and which one of the set is the default. The `code` is immutable, so no record carrying it is ever orphaned by an edit here — a merchant who retitles `call` to wording of their own changes what people READ and nothing about what `contact_events.kind` stores. Seeded values (`is_system`) are renameable like any other, and re-seeding leaves the rename alone.
     *
     * @param id The activity type to edit.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending.
     * @param title The fallback name shown when no locale matches.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersContactEventKindsUpdate(
        id: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        title: String? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contact-event-kinds/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * What the app.installed event runs. It fills all four of the value sets a tenant needs before anything else works — the payment terms, the address types, the lifecycle stages and the activity types — in one call. Idempotent by code: a set that already has its rows is left completely alone, so a re-delivered event and a merchant's renames both survive. A tenant installed before these tables existed is seeded lazily instead, by the first read that finds one empty.
     *
     * @param data Request body
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersDefaults(
        data: Any,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/defaults"

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
     * Where a company stands in the sales pipeline — a separate axis from status, and one whose steps are a sales team's own. A fresh install is seeded with lead, prospect, customer, churned, and the set seeds on first read too, so the page is never empty and `organizations.lifecycle_stage` always has a value it may carry. The whole set comes back in one page in the tenant's own order — this route takes no limit/offset/order and no column filters, so `page` describes the full set and `filter` is always empty.
     *
     * @return [Any]
     */
    suspend fun customersLifecycleStagesList(
    ): Any {
        val apiPath = "/v1/customers/lifecycle-stages"

        val apiParams = mutableMapOf<String, Any?>(
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
     * Extends this tenant's lifecycle stages set with a value of their own — the whole reason these four stopped being CHECK constraints. Where a company stands in the sales pipeline — a separate axis from status, and one whose steps are a sales team's own. The code is lowercase and becomes what `organizations.lifecycle_stage` stores; it cannot be changed afterwards, because every record carrying it would be orphaned.
     *
     * @param code What `organizations.lifecycle_stage` will store. Lowercase, starting with a letter; immutable afterwards.
     * @param title The fallback name shown when no locale matches.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted in the same call.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending. Default 0.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersLifecycleStagesCreate(
        code: String,
        title: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/lifecycle-stages"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * Takes a value out of the lifecycle stages set. There is no foreign key behind `organizations.lifecycle_stage` — one added to a table that starts empty fails the migration of every existing tenant — so this route IS the integrity: it refuses while any record still carries the code, and it refuses to empty the set. Retiring a value that is in use is therefore a two-step job: move the records onto another value first, then remove it.
     *
     * @param id The lifecycle stage to remove.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersLifecycleStagesDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/lifecycle-stages/{id}"
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
     * One value of the lifecycle stages set, by its id — its code, its fallback title, the per-language `labels` an operator reads and the badge `tone` a client renders it with. Where a company stands in the sales pipeline — a separate axis from status, and one whose steps are a sales team's own. Reading one value is the rare path: `GET /customers/lifecycle-stages` answers the whole set in a single page, which is what a select needs.
     *
     * @param id The lifecycle stage to read. Note that records store the CODE, not this id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersLifecycleStagesGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/lifecycle-stages/{id}"
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
     * Everything about a value except the value itself: its titles, its help text, its badge tone, its `position` in the select, and which one of the set is the default. The `code` is immutable, so no record carrying it is ever orphaned by an edit here — a merchant who retitles `customer` to wording of their own changes what people READ and nothing about what `organizations.lifecycle_stage` stores. Seeded values (`is_system`) are renameable like any other, and re-seeding leaves the rename alone.
     *
     * @param id The lifecycle stage to edit.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending.
     * @param title The fallback name shown when no locale matches.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersLifecycleStagesUpdate(
        id: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        title: String? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/lifecycle-stages/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * When a company has to pay. A wholesaler who agrees net 45 with one customer used to need a release of this app to say so. A fresh install is seeded with prepayment, direct_debit, net_7, net_14, net_30, net_60, net_90, and the set seeds on first read too, so the page is never empty and `organizations.payment_terms` always has a value it may carry. The whole set comes back in one page in the tenant's own order — this route takes no limit/offset/order and no column filters, so `page` describes the full set and `filter` is always empty.
     *
     * @return [Any]
     */
    suspend fun customersPaymentTermsList(
    ): Any {
        val apiPath = "/v1/customers/payment-terms"

        val apiParams = mutableMapOf<String, Any?>(
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
     * Extends this tenant's payment terms set with a value of their own — the whole reason these four stopped being CHECK constraints. When a company has to pay. A wholesaler who agrees net 45 with one customer used to need a release of this app to say so. The code is lowercase and becomes what `organizations.payment_terms` stores; it cannot be changed afterwards, because every record carrying it would be orphaned.
     *
     * @param code What `organizations.payment_terms` will store. Lowercase, starting with a letter; immutable afterwards.
     * @param title The fallback name shown when no locale matches.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted in the same call.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending. Default 0.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersPaymentTermsCreate(
        code: String,
        title: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/payment-terms"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * Takes a value out of the payment terms set. There is no foreign key behind `organizations.payment_terms` — one added to a table that starts empty fails the migration of every existing tenant — so this route IS the integrity: it refuses while any record still carries the code, and it refuses to empty the set. Retiring a value that is in use is therefore a two-step job: move the records onto another value first, then remove it.
     *
     * @param id The payment term to remove.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersPaymentTermsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/payment-terms/{id}"
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
     * One value of the payment terms set, by its id — its code, its fallback title, the per-language `labels` an operator reads and the badge `tone` a client renders it with. When a company has to pay. A wholesaler who agrees net 45 with one customer used to need a release of this app to say so. Reading one value is the rare path: `GET /customers/payment-terms` answers the whole set in a single page, which is what a select needs.
     *
     * @param id The payment term to read. Note that records store the CODE, not this id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersPaymentTermsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/payment-terms/{id}"
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
     * Everything about a value except the value itself: its titles, its help text, its badge tone, its `position` in the select, and which one of the set is the default. The `code` is immutable, so no record carrying it is ever orphaned by an edit here — a merchant who retitles `net_30` to wording of their own changes what people READ and nothing about what `organizations.payment_terms` stores. Seeded values (`is_system`) are renameable like any other, and re-seeding leaves the rename alone.
     *
     * @param id The payment term to edit.
     * @param description One line of help for whoever picks this value.
     * @param descriptions Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     * @param isDefault Promote this value; the previous default is demoted.
     * @param labels Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     * @param position Where it sits in the set, ascending.
     * @param title The fallback name shown when no locale matches.
     * @param tone Semantic badge colour.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersPaymentTermsUpdate(
        id: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        title: String? = null,
        tone: com.revenexx.enums.Tone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/payment-terms/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "descriptions" to descriptions,
            "is_default" to isDefault,
            "labels" to labels,
            "position" to position,
            "title" to title,
            "tone" to tone,
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
     * Discovery for the vocabulary routes: every enum this app publishes, each as a name, a title and a description. The VALUES are deliberately left out — this is the call that says which vocabularies exist, and the detail route is the one that answers what is in them. Names: address-types, contact-event-kinds, contact-statuses, lifecycle-stages, locales, organization-statuses, payment-terms, registration-statuses, roles, rule-matches, segment-sources. Fetch one with GET /customers/vocabularies/{name}; a client holding the qualified pair 'customers.<name>' builds that URL from the pair alone.
     *
     * @return [com.revenexx.models.VocabularyIndex]
     */
    suspend fun customersVocabulariesList(
    ): com.revenexx.models.VocabularyIndex {
        val apiPath = "/v1/customers/vocabularies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.VocabularyIndex = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.VocabularyIndex.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.VocabularyIndex::class.java,
            converter,
        )
    }


    /**
     * One vocabulary in full: every permitted value, each with its title, its description and the badge tone a client renders it with — enough to build a select without a second call. Two kinds of set, and 'source' says which one answered. 'schema' — the values are read out of the column's CHECK constraint, so the served set IS the enforced set and the two cannot drift; a value added to the constraint appears here even before anyone labels it, titled from its own key. 'table' — the values are the TENANT's own rows (payment terms, address types, lifecycle stages, activity types, roles), so they carry labels/descriptions per locale, is_system and is_default, and a merchant may add to them without a release of this app. 'tenant'/'defaults' are the two answers for a set the merchant configures but may not extend. Either way 'closed' is true: the set is exhaustive at this moment, so a value outside it is stale data rather than a missing label. Values come back in the order a select should offer them — lifecycle order for a status, the merchant's own position for a table. Names: address-types, contact-event-kinds, contact-statuses, lifecycle-stages, locales, organization-statuses, payment-terms, registration-statuses, roles, rule-matches, segment-sources.
     *
     * @param name The vocabulary name — the part after the dot in the qualified id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersVocabulariesGet(
        name: com.revenexx.enums.CustomersVocabulariesGetName,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/vocabularies/{name}"
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


}