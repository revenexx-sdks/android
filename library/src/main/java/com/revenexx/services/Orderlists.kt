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
 * Commerce Studio Order Lists App — saved, reusable position collections (shopping & label lists) that turn back into a cart or an order in one call. A list is owned by a contact and can be shared across the organization (shared; read-only unless the tenant makes shared lists team-editable). Whole positions are stored: article (product_id/sku), quantity, unit, price, tax rate, cost center, position texts and custom SKU. GET /orderlists returns the owner's own lists union the organization's shared lists; positions are managed as a nested items resource (list / add / bulk-replace / update / remove). POST /orderlists/{id}/cart hands the positions to the carts app, POST /orderlists/{id}/order to the orders app. Both collections answer the platform envelope { items, page, filter } with limit/offset/order.
 */
class Orderlists(client: Client) : Service(client) {

    /**
     * What a caller may see is a UNION, not an intersection: the lists this contact owns, plus the lists their organization shares — `owner_id = X OR (organization_id = Y AND shared)`. A list that satisfies both sides is merged by id and counted once. Where the gateway resolved an acting contact, that contact and their organization ARE the scope and neither `owner_id` nor `organization_id` in the query can widen it; without a resolved principal — a back-office caller holding the tenant key — the two are read from the query, and a call that names neither sees every list the tenant keeps. Three filters are read in all — `owner_id`, `organization_id`, `kind` — and any OTHER query key is ignored rather than refused, which is what the `filter` echo makes visible: a key that is missing there was not applied. When only one side of the predicate is in play the database pages the rows and reports the true total; when both are, each side is read separately and bounded at a thousand rows, merged, and paged after the merge, so `total` is the size of the merged set rather than a database count. The default sort is `updated_at.desc`, which is why adding a position moves its list to the front of the page. Every row carries `item_count`. Without it the only way to render a per-list badge was to read the positions of every list on the page — thousands of rows to draw twenty numbers. The count is bounded the way the page is: at most 200 lists, each capped by the tenant's max_items_per_list.
     *
     * @param ownerId Exact-match filter on `owner_id`. Every list one contact owns. Ignored when the gateway resolved an acting contact — the scope is then that contact and a query parameter cannot widen it.
     * @param organizationId Exact-match filter on `organization_id`. The SHARED lists of one organization. Combined with `owner_id` this is a union, not an intersection: own lists ∪ that organization's shared ones.
     * @param kind Filter by list kind — a `code` from GET /orderlists/kinds. A code this tenant does not keep is a 400 naming the ones it does, so this is the one filter here that can fail.
     * @param limit Page size (default 50, max 200). A larger value is clamped rather than refused.
     * @param offset Row offset for pagination (default 0). Page with `page.total` and `page.hasMore`.
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsList(
        ownerId: String? = null,
        organizationId: String? = null,
        kind: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists"

        val apiParams = mutableMapOf<String, Any?>(
            "owner_id" to ownerId,
            "organization_id" to organizationId,
            "kind" to kind,
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
     * Three fields are required, and they are exactly the columns the database will not fill in: `name`, `owner_id` and `owner_name`. Everything else has an answer already — `kind` resolves to the caller's value, else the market's `default_kind` setting, else the kind the tenant flagged; `shared` is false; `organization_id` is null, which makes `shared` meaningless because there is then nobody to share with. Nothing about a list is unique: one owner may keep two lists with the same name, and the same article may appear in as many lists as the buyer wants. The list may be created empty or pre-filled in the same call: an optional `items` array is written as the list's positions with the row, so a twenty-line list is one request rather than a create followed by twenty adds, and the array order is the position order. Those initial `items` are normalized and article-checked BEFORE the list row is written, and both caps are checked first as well — the tenant's `max_items_per_list` against the array, and its `max_lists_per_owner` against what this contact already keeps — so a rejected position never leaves an empty list behind and a contact at their limit is refused before anything is inserted. The owner is set once — no route moves a list to another contact.
     *
     * @param name What the buyer calls this list. Free text, at least one character, and not unique: two contacts may both keep a "Weekly office supplies". It is also the name a NEW cart gets when POST /orderlists/{id}/cart creates one.
     * @param ownerId The contact who owns the list. Ownership IS the authorization here: a caller the gateway resolved to a contact sees their own lists plus their organization's shared ones, and may write only their own — unless `shared_lists_editable` opens a shared list to the whole owning organization. Set once at create; no route moves a list to another owner.
     * @param ownerName The owner's display name as it stood when the list was created — a snapshot, so renaming the contact does not rewrite it. Carried so a shared list can say whose it is without a call to the contacts app.
     * @param items Optional initial positions. Every one is validated — and article-checked where `reject_unknown_articles` is on — BEFORE the list row is written, so a rejected position never leaves an empty list behind.
     * @param kind List kind — the `code` of one of the tenant's own kinds (GET /orderlists/kinds); defaults to the flagged one, or the market's 'default_kind' setting.
     * @param metadata Free-form data the tenant keeps on the list — an ERP requisition number, a department, whatever an integration needs to recognise the list again. Never read by this app, and never merged: a write replaces the whole document.
     * @param organizationId The organization the sharing is scoped to. Null means the list can only ever be the owner's own: `shared` is meaningless without it, because there is no set of people to share with. It is also what the order conversion hands the orders app as the buying organization.
     * @param shared Whether the OWNING ORGANIZATION may see this list. False — the default — keeps it private to `owner_id`, and a foreign private list answers 404 rather than 403, so an outsider learns nothing from the difference. True lets every contact of `organization_id` READ it, and write it only where the tenant turned on the `shared_lists_editable` setting. A list with no `organization_id` shares with nobody however this is set.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsCreate(
        name: String,
        ownerId: String,
        ownerName: String,
        items: List<com.revenexx.models.OrderListItemInput>? = null,
        kind: String? = null,
        metadata: Any? = null,
        organizationId: String? = null,
        shared: Boolean? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "kind" to kind,
            "metadata" to metadata,
            "name" to name,
            "organization_id" to organizationId,
            "owner_id" to ownerId,
            "owner_name" to ownerName,
            "shared" to shared,
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
     * Seeds the two kinds a fresh tenant starts with — `shopping` and `label` — and gives `shopping` the default flag. Idempotent by code: `created` names the kinds this call wrote, `existing` the ones that were already there and were left exactly as the tenant keeps them, renamed, retoned and reordered included. On a settled tenant `created` is empty. It is rarely the call you need — the `app.installed` event runs the same seed, and the first read of GET /orderlists/kinds on an empty table seeds before it answers. It never removes a kind and never restores one a merchant deleted.
     *
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsDefaults(
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/defaults"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * What a saved list may be FOR — the tenant's own taxonomy, and the set every `kind` on a list is drawn from. This used to be a CHECK constraint, which meant a merchant who keeps reagent lists or sample lists needed a release of this app to say so — and the app never branched on the value, it only checked membership. The set is the tenant's rows now. Reading this route on a tenant that has none seeds them, so it never answers an empty set on a fresh install and a client may treat the first read as the install step it no longer has to make. Rows come back in `position` order, ascending, which is the order a select should offer them in, and each carries the `is_default` flag that decides what a create with no `kind` falls back to. It takes NO filters: `limit` and `offset` are the only query keys it reads, and any other is ignored rather than refused — which is also why this collection alone answers no `filter` echo, since echoing an empty one would be noise. The `code` on each row, not the `id`, is what `lists.kind` stores and what `?kind=` on GET /orderlists matches.
     *
     * @param limit Page size (default 50, max 200). A larger value is clamped rather than refused.
     * @param offset Row offset for pagination (default 0).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun orderlistsKindsList(
        limit: Long? = null,
        offset: Long? = null,
    ): Any {
        val apiPath = "/v1/orderlists/kinds"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
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
     * Adds a kind to the tenant's own taxonomy — reagent lists, sample lists, whatever a merchant sorts their saved lists by — without a release of this app, because nothing here branches on the value. `code` and `title` are required, and they are exactly the two columns of `list_kinds` the database will not fill in. The code is lowercased on the way in and immutable afterwards: renaming it would orphan every list carrying it, since a list stores the code and not the id. `is_default: true` promotes the new kind and demotes whoever held the flag. Creating a kind changes no existing list.
     *
     * @param code What `lists.kind` will store. Lowercased on the way in and immutable afterwards — a merchant who wants a different code creates a new kind and moves the lists over.
     * @param title What a person reads. `labels` adds the localized forms on top; this one is the fallback.
     * @param description What this kind is for, in one sentence — the line a select shows under the title.
     * @param descriptions Localized descriptions, keyed by language tag.
     * @param isDefault Promote this kind; the previous default is demoted.
     * @param labels Localized titles, keyed by language tag.
     * @param position Where the kind sits in a select, ascending. Omitted means 0, which puts it first among the unpositioned.
     * @param tone Semantic badge colour. The client owns what each tone looks like; omitted means `neutral`.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsKindsCreate(
        code: String,
        title: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        tone: com.revenexx.enums.OrderListKindTone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/kinds"

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
     * There is no foreign key behind `lists.kind` — it is a plain text column holding a code, and nothing in the database points at `list_kinds` — so this route's own 409 is the whole of the referential integrity. It reads whether any list still carries the code and refuses if one does, and refuses again when this is the last kind left, because a list must have one. Nothing cascades and no list is rewritten. Two gaps the guard leaves: it is a read followed by a delete with no lock between them, so a list written with the code in that window survives it; and the market-scoped `default_kind` SETTING is neither consulted nor cleared, so deleting the kind it names leaves the setting pointing at nothing while creates fall through to whichever kind holds the default flag. A list that does end up naming a code nothing defines is not broken, only stranded: it is still returned by GET /orderlists and GET /orderlists/{id} carrying the bare code, the vocabulary no longer offers that value so a UI renders the code itself, `?kind=` refuses it with a 400 naming the codes that remain, and the way back is PUT /orderlists/{id} with a kind the tenant keeps. Deleting the flag-holder hands the flag to the first remaining kind. The answer is the `code`, not the `{deleted, id}` the other deletes here return.
     *
     * @param id The list kind, by id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsKindsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/kinds/{id}"
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
     * One kind, by the id this route takes. The `code` is the OTHER identity and the one that matters to the data: `lists.kind` stores the code and never this id, so a list is joined to its kind by code while every /orderlists/kinds/{id} route is addressed by uuid. A fresh tenant starts with two — `shopping` and `label`, seeded on install — and everything beyond them is the merchant's own. A kind seeded before 0.15.0 may hold a serialized locale map in `title` and `description` where plain text belongs; those rows were left as they stand, because repairing them is a data change.
     *
     * @param id The list kind, by id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsKindsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/kinds/{id}"
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
     * Everything a kind has except its code: the title a person reads, the sentence underneath it, the localized forms of both, the badge tone, and where it sits in a select. The code is not among them and cannot be reached from here at all: sending a different one is a 400 rather than a silent no-op, because `lists.kind` stores the code and a rename would orphan every list that carries it with no foreign key to stop it. So a rename is never how a list comes to name a code nothing defines — only a delete can do that. Renaming the TITLE touches no list, for the same reason. A blank title is ignored rather than stored; an explicit null clears the description; `labels` and `descriptions` replace the whole map rather than merging into it. `is_default: true` makes the same move POST /orderlists/kinds/{id}/make-default makes on its own. A system kind is editable like any other.
     *
     * @param id The list kind, by id.
     * @param description What this kind is for, in one sentence. Explicit null clears it.
     * @param descriptions Localized descriptions, keyed by language tag. Replaces the whole map rather than merging into it.
     * @param isDefault True promotes this kind and demotes the previous default — the same move POST /orderlists/kinds/{id}/make-default makes on its own.
     * @param labels Localized titles, keyed by language tag. Replaces the whole map rather than merging into it.
     * @param position Where the kind sits in a select, ascending.
     * @param title What a person reads. A blank title is ignored rather than stored — a kind with no words is unreadable in every UI.
     * @param tone Semantic badge colour. The client owns what each tone looks like.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsKindsUpdate(
        id: String,
        description: String? = null,
        descriptions: Any? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        title: String? = null,
        tone: com.revenexx.enums.OrderListKindTone? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/kinds/{id}"
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
     * One call MOVES the flag: the kind in the path is promoted and whoever held the flag before is demoted in the same request, because the flag is a single answer and not a per-row opinion. It is what a list created without a kind falls back to, so two defaults leave the result to row order and none leaves it to whatever sorts first — which is exactly why promotion and demotion cannot be two calls a client makes in sequence. PUT with is_default already moved it, but only as a side effect of an edit, and a client promoting and then demoting by hand produces those two broken states whenever one of the pair does not land. Every kind the tenant keeps is walked, and only the rows whose flag is wrong are written — the new default if it was not already set, the old one if it was — so the call costs at most two writes and repeating it costs none, which makes it safe to retry. The kind's other fields are untouched and no existing list is rewritten: lists that already name a kind keep it, since the flag decides only what a FUTURE create with no `kind` resolves to. The market-scoped `default_kind` setting still wins where it is set; this flag is the tenant-wide answer underneath it.
     *
     * @param id The list kind, by id.
     * @param data Request body
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsKindsMakeDefault(
        id: String,
        data: Any,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/kinds/{id}/make-default"
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
     * Discovery for the vocabulary routes, and nothing more: every enum this app publishes, each as a name plus the words a person reads for it — its title and its description — and never the values, which are one call further down at GET /orderlists/vocabularies/{name}. It exists so that a client holding a qualified pair like 'orderlists.kinds' can build that URL from the pair alone and keep no copy of an enum of its own. Names: kinds. The split is deliberate rather than an economy: the set of NAMES is fixed by a release of this app, so a client may cache this answer for as long as it caches the contract, while the values under 'kinds' are the tenant's own rows and change without a release — which is why this route says nothing about them and why a UI building a select must make the second call rather than read the values off here. Title and description come back either as a plain string or as a locale map keyed by language tag, so a client reads the tag it wants and falls back to `en` — the same shape every localized field in this app carries.
     *
     * @return [com.revenexx.models.OrderListVocabularyIndex]
     */
    suspend fun orderlistsVocabulariesList(
    ): com.revenexx.models.OrderListVocabularyIndex {
        val apiPath = "/v1/orderlists/vocabularies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.OrderListVocabularyIndex = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderListVocabularyIndex.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderListVocabularyIndex::class.java,
            converter,
        )
    }


    /**
     * One named enum with every value it permits, and enough about each value to render it without a second source: the `key` the database stores and enforces, the title and the description a person reads, and the semantic badge `tone` a UI colours it with — which is why no client needs a colour map of its own, and why the Cockpit's hand-kept one could go. A value that names no tone of its own inherits the vocabulary's `default_tone`, so the field is never empty. 'kinds' is table-backed: the tenant's own rows ARE the value set, so a value they added appears here without a release of this app, and each value carries its `labels`, `descriptions` and the `is_default` flag besides. Values come back in `position` order, which is the order a select should offer. 'closed' says the set is exhaustive at this moment, so a value outside it is stale data rather than a missing label — what changed with the move to a table is WHO may extend it, not whether the set is closed. `source` says which: 'schema' where a CHECK constraint owns the values, 'table' where the tenant's rows do. Names: kinds.
     *
     * @param name The vocabulary name — the part after the dot in the qualified id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsVocabulariesGet(
        name: com.revenexx.enums.OrderlistsVocabulariesGetName,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/vocabularies/{name}"
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
     * Takes every position with it, in the database: `items.list_id` is the app's only foreign key and it is ON DELETE CASCADE, and the handler removes the positions explicitly first besides. Nothing survives the list, there is no soft delete and no undo — and the answer carries no count, so read the list (or its `item_count`) BEFORE the call if you need to know how much went. What it does NOT take is what the list has already produced: a cart line or an order position built by the conversions carries `order_list_id`, `order_list_name` and `order_list_item_id` in its snapshot, and those are jsonb values inside another app rather than foreign keys — ADR-0055 forbids a cross-app FK, so nothing cascades there and nothing is nulled. The cart and the order are unharmed, because every position was copied as a snapshot rather than referenced; the provenance link is what dangles, permanently.
     *
     * @param id The order list, by id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{id}"
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
     * The whole list in one call: the row plus every position inline, in `position` order, up to a thousand of them. The nested positions collection exists to CHANGE the positions, not to page them, so this is the read a detail view makes. Reading is wider than writing here — an acting contact sees their own lists and their organization's shared ones, and a list that is neither answers 404 rather than 403, so an outsider learns nothing from the difference. The row carries the dead `public` column next to `shared`; read `shared`.
     *
     * @param id The order list, by id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{id}"
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
     * Rename, share or reclassify — the whole of what a list says about itself, plus `metadata`. Positions go through the items routes and the owner cannot be changed by anything. `shared` is what the column `public` was renamed to in June 2026; `public` is still on the wire because the provisioner is additive, is false on every row written since, and says nothing about who may see the list. One trap: a `kind` this tenant does not keep is IGNORED rather than refused, so the list quietly keeps the kind it had and a client that cares must read the answer back. An empty body is a 400 rather than a no-op.
     *
     * @param id The order list, by id.
     * @param kind List kind — the `code` of one of the tenant's own kinds (GET /orderlists/kinds); defaults to the flagged one, or the market's 'default_kind' setting.
     * @param metadata Free-form data the tenant keeps on the list — an ERP requisition number, a department, whatever an integration needs to recognise the list again. Never read by this app, and never merged: a write replaces the whole document.
     * @param name What the buyer calls this list. Free text, at least one character, and not unique: two contacts may both keep a "Weekly office supplies". It is also the name a NEW cart gets when POST /orderlists/{id}/cart creates one.
     * @param shared Whether the OWNING ORGANIZATION may see this list. False — the default — keeps it private to `owner_id`, and a foreign private list answers 404 rather than 403, so an outsider learns nothing from the difference. True lets every contact of `organization_id` READ it, and write it only where the tenant turned on the `shared_lists_editable` setting. A list with no `organization_id` shares with nobody however this is set.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsUpdate(
        id: String,
        kind: String? = null,
        metadata: Any? = null,
        name: String? = null,
        shared: Boolean? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "kind" to kind,
            "metadata" to metadata,
            "name" to name,
            "shared" to shared,
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
     * The reason a buyer keeps a list at all: every position of the list goes into a cart in one call. The cart is either one the caller names or one this call makes. Sending 'cart_id' adds to that existing cart; omitting it creates a cart for the LIST'S OWNER — not for whoever called — names it after the list, and makes it that owner's current cart, because a cart the buyer cannot see is not 'added to cart'. Which of the two happened is not left to be inferred: `cart_created` says so and `cart_id` names the cart either way. 'append' (the default, tenant-configurable through `cart_merge_mode`) lets the carts app merge each line by product and price so quantities accumulate, and is sent one line at a time precisely because that merge happens on add; 'replace' makes the list the cart's whole contents in one call. What the cart has no column for — cost centre, custom SKU, position texts — rides in each line's snapshot together with the list it came from. The list itself is never touched: it is read, not emptied, so the same list converts again next month. Cross-app: carts.create, carts.items.create, carts.items.replace.
     *
     * @param id The order list, by id.
     * @param cartId Add to this existing cart. Omit to create one for the list owner and make it their current cart.
     * @param currency ISO 4217 code for the cart and its lines. Omit to let the carts app decide.
     * @param mode 'append' adds the positions (the carts app merges a line by product and price, so quantities accumulate); 'replace' makes the list the cart's entire contents. Defaults to the tenant's 'cart_merge_mode' setting.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsToCart(
        id: String,
        cartId: String? = null,
        currency: String? = null,
        mode: com.revenexx.enums.OrderListCartMode? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{id}/cart"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "cart_id" to cartId,
            "currency" to currency,
            "mode" to mode,
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
     * The other half of the reason a list exists — and it is the ORDERS app that does it, over the gateway rather than over a shared table, so everything an order means is that app's answer and not this one's. Places the list's positions as an order: buyer and organization come from the list, the cost centre and the position texts land on the order's own columns, and the list is left exactly as it stands so it can be ordered again next month. The acting contact is re-asserted on the call, so the orders app applies ITS rules to the BUYER rather than to this app — a contact holding only orders.request, or an order above the tenant's approval threshold, comes back with status 'pending' and no placed_at instead of being refused. That pending order is the platform's nearest thing to a draft; the orders app owns the state and this one cannot override it, which is why `status` is reported rather than chosen and why the created order is handed back verbatim under `order` beside the three fields lifted out of it. Cross-app: orders.place.
     *
     * @param id The order list, by id.
     * @param currency ISO 4217 code. Omit to let the orders app apply the market default.
     * @param customerOrderNumber The BUYER's own order or purchase-order number, forwarded to the orders app verbatim. Free text and never generated here: it exists so the paperwork can carry the number the buyer's accounts payable will look for.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsToOrder(
        id: String,
        currency: String? = null,
        customerOrderNumber: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{id}/order"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "currency" to currency,
            "customer_order_number" to customerOrderNumber,
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
     * Every column of a position is an exact-match filter — eighteen of them, which is the whole row — and they combine as AND. `list_id` is not among them: it comes from the path and overwrites anything the query says. The default sort is `position.asc`, and `position` is neither dense nor unique: removing a position leaves its number behind while the next add takes the list's current COUNT, so a delete from the middle followed by an add produces two rows sharing a number and the tie falls to whatever the database returns first. Sort by `created_at` where the order has to be unambiguous.
     *
     * @param listId The list the position belongs to. An id no list in this tenant has — or one the caller may not read — answers 404.
     * @param id Exact-match filter on `id`. The position's own id — the same row GET /orderlists/{list_id}/items/{id} answers, reached through the collection.
     * @param productId Exact-match filter on `product_id`. Every position for one catalogue product.
     * @param sku Exact-match filter on `sku`. One article number as the catalogue knows it.
     * @param name Exact-match filter on `name`. The saved article name, matched EXACTLY and case-sensitively — this is equality, not a search.
     * @param image Exact-match filter on `image`. The snapshotted image URL. Exact match, so this is a reconciliation tool rather than something a person types.
     * @param quantity Exact-match filter on `quantity`. An exact quantity, which is a needle-in-a-haystack filter — there is no range filter on this collection.
     * @param unit Exact-match filter on `unit`. One unit, in the tenant's own words. Open text, so the value must match what was written.
     * @param price Exact-match filter on `price`. An exact snapshotted unit price. Equality on a decimal, so it finds the rows written at exactly this price and nothing near it.
     * @param taxRate Exact-match filter on `tax_rate`. An exact VAT rate as a percent (19 = 19 %).
     * @param costCenterId Exact-match filter on `cost_center_id`. Every position booked to one cost centre, as the tenant's ERP names it. The filter a controller uses to see what a department has saved up.
     * @param positionTexts Exact-match filter on `position_texts`. The whole notes ARRAY, serialized as JSON — equality on the document, not a search inside it.
     * @param customSku Exact-match filter on `custom_sku`. The buyer's own article number. The lookup a B2B buyer actually performs: their purchasing system knows this number and not the shop's.
     * @param categorySlug Exact-match filter on `category_slug`. One catalogue category, as a slug.
     * @param subcategorySlug Exact-match filter on `subcategory_slug`. One catalogue subcategory, as a slug.
     * @param position Exact-match filter on `position`. The exact sort position within the list.
     * @param metadata Exact-match filter on `metadata`. The WHOLE metadata document, serialized as JSON — equality, not a key lookup and not a containment query.
     * @param createdAt Exact-match filter on `created_at`. The exact creation timestamp. There is no range filter here; sort with `order=created_at.desc` instead.
     * @param updatedAt Exact-match filter on `updated_at`. The exact timestamp of the last change.
     * @param limit Page size (default 50, max 200). A larger value is clamped rather than refused.
     * @param offset Row offset for pagination (default 0). Page with `page.total` and `page.hasMore`.
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsItemsList(
        listId: String,
        id: String? = null,
        productId: String? = null,
        sku: String? = null,
        name: String? = null,
        image: String? = null,
        quantity: Double? = null,
        unit: String? = null,
        price: Double? = null,
        taxRate: Double? = null,
        costCenterId: String? = null,
        positionTexts: String? = null,
        customSku: String? = null,
        categorySlug: String? = null,
        subcategorySlug: String? = null,
        position: Long? = null,
        metadata: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{list_id}/items"
            .replace("{listId}", listId)

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "product_id" to productId,
            "sku" to sku,
            "name" to name,
            "image" to image,
            "quantity" to quantity,
            "unit" to unit,
            "price" to price,
            "tax_rate" to taxRate,
            "cost_center_id" to costCenterId,
            "position_texts" to positionTexts,
            "custom_sku" to customSku,
            "category_slug" to categorySlug,
            "subcategory_slug" to subcategorySlug,
            "position" to position,
            "metadata" to metadata,
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
     * A position is a whole saved line, not a pointer at a product. `name` is required and one of `product_id` / `sku` must be set — the two things the database itself insists on — and everything else is a snapshot of what the buyer saw. Nothing here deduplicates: adding the same article twice makes two positions, because it is the CART that merges lines by product and price, not the list. The new row takes the list's current position COUNT unless the payload names a `position` of its own, so it collides with an existing number whenever an earlier position was deleted from the middle. The list's `updated_at` is touched, which is what the default sort of GET /orderlists reads.
     *
     * @param listId The list the position belongs to. An id no list in this tenant has — or one the caller may not read — answers 404.
     * @param name The article name AS IT WAS when the position was saved. A snapshot on purpose: the list is the buyer's own record, so a renamed or withdrawn article still reads the way they wrote it down.
     * @param categorySlug The catalogue category the article sat in when the position was saved, as a slug. Kept so a long list can be grouped the way the shop groups it without a call to the catalogue.
     * @param costCenterId The cost centre this position books to, as the tenant's ERP names it. Free text and not our enum. It survives into the ORDER position, which has a `cost_center` column; a CART line has none, so the cart conversion carries it in the line snapshot instead.
     * @param customSku The buyer's OWN article number for this article — what their purchasing system calls it, which is rarely what the shop calls it. Free text, and the field a B2B buyer searches their own lists by.
     * @param image The article image at the time the position was saved, as a URL or a path — a snapshot like `name`, and nothing here refreshes it. It rides into the cart line and the order position in their snapshot, because neither has a column for it.
     * @param metadata Free-form data the tenant keeps on the position. Never read by this app; it travels into the cart line / order position snapshot untouched. A write replaces the whole document rather than merging into it.
     * @param position Sort order within the list, ascending — the order the positions collection returns by default and the order the conversions hand the lines over in. Neither dense nor unique: an add with no `position` of its own takes the list's current position COUNT, so removing a position from the middle and adding another leaves two rows sharing a number. A bulk replace assigns the array index the same way, so it renumbers only the positions it is not given explicitly.
     * @param positionTexts Per-position notes the buyer wrote — an engraving, a delivery instruction, a reference for the picker. An ARRAY OF STRINGS, one entry per line; the order conversion joins them with newlines into the order position's single `position_text`, and the cart conversion carries the array in the line snapshot.
     * @param price Unit price snapshot — what the buyer saw when they saved the position, in whatever way the catalogue quoted it. It is a record, not a live price: the cart and the order reprice on their own terms, so this never becomes what somebody is charged.
     * @param productId The catalogue product this position stands for. One of `product_id` / `sku` must be set (the database enforces it); this is the identity the products app answers to, and the one `reject_unknown_articles` and the conversions check against.
     * @param quantity How much of the article the list holds. Greater than zero — the database refuses the rest — and fractional to three decimals, because a B2B position may be 2.5 metres or 0.75 kilos.
     * @param sku The article number as the catalogue knows it — the alternative identity to `product_id`, and the one an ERP integration usually joins on.
     * @param subcategorySlug The catalogue subcategory, as a slug. Same purpose as `category_slug`, one level down.
     * @param taxRate The VAT rate that applied when the position was saved, as a PERCENT (19 = 19 %). Four decimals so a rate like 8.25 % survives; carts and orders document the same field the same way, and the conversion forwards the number unchanged.
     * @param unit The unit `quantity` counts in, in the tenant's own words. Deliberately open text and deliberately NOT a vocabulary: a B2B catalogue units in pieces, metres, kilos, rolls and pallets, and any closed list published here would be a guess.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsItemsCreate(
        listId: String,
        name: String,
        categorySlug: String? = null,
        costCenterId: String? = null,
        customSku: String? = null,
        image: String? = null,
        metadata: Any? = null,
        position: Long? = null,
        positionTexts: List<String>? = null,
        price: Double? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
        subcategorySlug: String? = null,
        taxRate: Double? = null,
        unit: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{list_id}/items"
            .replace("{listId}", listId)

        val apiParams = mutableMapOf<String, Any?>(
            "category_slug" to categorySlug,
            "cost_center_id" to costCenterId,
            "custom_sku" to customSku,
            "image" to image,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "position_texts" to positionTexts,
            "price" to price,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
            "subcategory_slug" to subcategorySlug,
            "tax_rate" to taxRate,
            "unit" to unit,
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
     * Set semantics: what you send becomes the list's positions and everything else is deleted. Ids are NOT preserved — every row is dropped and rewritten, so a client holding position ids must re-read them — and an empty array empties the list. Both guards run before the first delete, so an oversized or unknown-article replace answers 400 with the list still holding exactly what it held. It is not a renumbering call: an entry that names no `position` takes its array index, one that names its own keeps it, so the array order is the default rather than an override. Writing is narrower than reading: the owner may always replace, and anyone else only when the list is shared with their own organization AND the tenant turned `shared_lists_editable` on — otherwise a caller who can READ the list through the sharing rule is answered 403 here. The delete-then-insert is not wrapped in a transaction of its own, so a client should treat a failed replace as a list of unknown contents and re-read it rather than retry blind. The answer is the whole new set in the same paged envelope every other collection uses, with `limit`, `offset` and `total` describing exactly what was written; the list's `updated_at` is touched, which moves it to the front of the default GET /orderlists page.
     *
     * @param listId The list the position belongs to. An id no list in this tenant has — or one the caller may not read — answers 404.
     * @param items The new full set of positions, in the order they should carry. An empty array empties the list. Every existing position is deleted and rewritten, so ids are NOT preserved. The array order is the DEFAULT and not an override: an entry that names no `position` takes its index, one that names its own keeps it — so a replace does not by itself renumber the list from zero.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsItemsReplace(
        listId: String,
        items: List<com.revenexx.models.OrderListItemInput>,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{list_id}/items"
            .replace("{listId}", listId)

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
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
     * Removes one saved line and takes nothing with it — no foreign key in this app points at a position. What it leaves behind is the gap: every remaining row keeps the number it had, and the next add takes the list's COUNT as its `position`, so a removal from the middle sets up a later collision. A bulk replace is the only call that rewrites the sequence. Outside this app, a cart line or order position built from this row still carries `order_list_item_id` in its snapshot — a jsonb value, not a reference — so it is simply left naming a row that is gone. The list's `updated_at` is touched.
     *
     * @param listId The list the position belongs to. An id no list in this tenant has — or one the caller may not read — answers 404.
     * @param id The position, by id. A position that belongs to another list answers 404.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsItemsDelete(
        listId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{list_id}/items/{id}"
            .replace("{listId}", listId)
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
     * One saved line by its own id, in exactly the shape the collection returns — there is nothing here the collection does not already give you, so this is the read for a client that holds a position id and nothing else. The list in the path is enforced rather than decorative: a position that belongs to a different list answers 404 rather than the row, which is what stops an id lifting a position out of a list the caller may not read. An unknown or unreadable list is a 404 before the position is looked at.
     *
     * @param listId The list the position belongs to. An id no list in this tenant has — or one the caller may not read — answers 404.
     * @param id The position, by id. A position that belongs to another list answers 404.
     * @return [com.revenexx.models.Error]
     */
    suspend fun orderlistsItemsGet(
        listId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{list_id}/items/{id}"
            .replace("{listId}", listId)
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
     * A partial update: omitted fields keep the value they have, and an explicit null is the only way to clear one. `quantity` is re-checked (> 0), and where `reject_unknown_articles` is on the article is re-checked against the MERGED row rather than the payload — so changing only the name cannot smuggle an unknown article past the guard that the create applied. `position` is set, not shifted: writing 3 puts this row at 3 and moves nothing else, which is the other way two positions come to share a number. The list's `updated_at` is touched.
     *
     * @param listId The list the position belongs to. An id no list in this tenant has — or one the caller may not read — answers 404.
     * @param id The position, by id. A position that belongs to another list answers 404.
     * @param categorySlug The catalogue category the article sat in when the position was saved, as a slug. Kept so a long list can be grouped the way the shop groups it without a call to the catalogue.
     * @param costCenterId The cost centre this position books to, as the tenant's ERP names it. Free text and not our enum. It survives into the ORDER position, which has a `cost_center` column; a CART line has none, so the cart conversion carries it in the line snapshot instead.
     * @param customSku The buyer's OWN article number for this article — what their purchasing system calls it, which is rarely what the shop calls it. Free text, and the field a B2B buyer searches their own lists by.
     * @param image The article image at the time the position was saved, as a URL or a path — a snapshot like `name`, and nothing here refreshes it. It rides into the cart line and the order position in their snapshot, because neither has a column for it.
     * @param metadata Free-form data the tenant keeps on the position. Never read by this app; it travels into the cart line / order position snapshot untouched. A write replaces the whole document rather than merging into it.
     * @param name The article name AS IT WAS when the position was saved. A snapshot on purpose: the list is the buyer's own record, so a renamed or withdrawn article still reads the way they wrote it down.
     * @param position Sort order within the list, ascending — the order the positions collection returns by default and the order the conversions hand the lines over in. Neither dense nor unique: an add with no `position` of its own takes the list's current position COUNT, so removing a position from the middle and adding another leaves two rows sharing a number. A bulk replace assigns the array index the same way, so it renumbers only the positions it is not given explicitly.
     * @param positionTexts Per-position notes the buyer wrote — an engraving, a delivery instruction, a reference for the picker. An ARRAY OF STRINGS, one entry per line; the order conversion joins them with newlines into the order position's single `position_text`, and the cart conversion carries the array in the line snapshot.
     * @param price Unit price snapshot — what the buyer saw when they saved the position, in whatever way the catalogue quoted it. It is a record, not a live price: the cart and the order reprice on their own terms, so this never becomes what somebody is charged.
     * @param productId The catalogue product this position stands for. One of `product_id` / `sku` must be set (the database enforces it); this is the identity the products app answers to, and the one `reject_unknown_articles` and the conversions check against.
     * @param quantity How much of the article the list holds. Greater than zero — the database refuses the rest — and fractional to three decimals, because a B2B position may be 2.5 metres or 0.75 kilos.
     * @param sku The article number as the catalogue knows it — the alternative identity to `product_id`, and the one an ERP integration usually joins on.
     * @param subcategorySlug The catalogue subcategory, as a slug. Same purpose as `category_slug`, one level down.
     * @param taxRate The VAT rate that applied when the position was saved, as a PERCENT (19 = 19 %). Four decimals so a rate like 8.25 % survives; carts and orders document the same field the same way, and the conversion forwards the number unchanged.
     * @param unit The unit `quantity` counts in, in the tenant's own words. Deliberately open text and deliberately NOT a vocabulary: a B2B catalogue units in pieces, metres, kilos, rolls and pallets, and any closed list published here would be a guess.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun orderlistsItemsUpdate(
        listId: String,
        id: String,
        categorySlug: String? = null,
        costCenterId: String? = null,
        customSku: String? = null,
        image: String? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        positionTexts: List<String>? = null,
        price: Double? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
        subcategorySlug: String? = null,
        taxRate: Double? = null,
        unit: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/orderlists/{list_id}/items/{id}"
            .replace("{listId}", listId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "category_slug" to categorySlug,
            "cost_center_id" to costCenterId,
            "custom_sku" to customSku,
            "image" to image,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "position_texts" to positionTexts,
            "price" to price,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
            "subcategory_slug" to subcategorySlug,
            "tax_rate" to taxRate,
            "unit" to unit,
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