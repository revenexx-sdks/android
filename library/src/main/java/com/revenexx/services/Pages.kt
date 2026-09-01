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
 * The records this app stores, addressed by id and edited outside the visual editor: pages and their publish history, the menus a theme renders as navigation, the block templates a new page can start from, the library of block subtrees many pages share, and the one seeding call a theme activation hook fires. A page here is its METADATA — title, slug, status, type — never its blocks; the blocks live in the editor group, because changing one is a mutation and not a field update. The vocabularies that name the permitted values of a status column are here too.
 */
class Pages(client: Client) : Service(client) {

    /**
     * The pool an editor picks a reusable block from. A library item is ONE block subtree that many pages share BY REFERENCE — edit the item and every page using it changes — which is what separates it from a template, the other reusable thing here, which copies instead and is at `GET /pages/templates`. So the two filters are the two questions the picker asks: `bundles` narrows to the block types that fit the field being filled, `text` matches the label a person gave the item.
     *
     * @param limit Page size (default 24, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @param bundles Comma-separated block types; an item matching any of them is returned. Note the plural — `?bundle=` (singular) is not read by this route and is ignored. Empty means no filter.
     * @param text Case-insensitive substring search over the item label. Runs in the query, so `page.total` counts the matches. Empty means no search.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesLibraryList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        bundles: String? = null,
        text: String? = null,
    ): Any {
        val apiPath = "/v1/pages/library"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "bundles" to bundles,
            "text" to text,
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
     * Retires a reusable block. It leaves the picker and every list, but the blocks pointing at it keep their `library_item_id` — the FK's `set null` belongs to a hard delete, and this writes a tombstone. Delivery then skips the expansion for a struck item rather than failing on it, so a page that used it falls back to the block content stored in its own published revision: nothing breaks, but the pages quietly stop tracking each other. Nothing here tells you which pages those are, so establish that before striking it.
     *
     * @param id The library item id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesLibraryDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/library/{id}"
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
     * The stored subtree behind one reusable block, so a picker can preview what dropping it into a page would produce. Because delivery expands the reference against THIS row at read time, what comes back is also what every page already using the item is currently rendering — which makes this the call to make before editing one.
     *
     * @param id The library item id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesLibraryGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/library/{id}"
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
     * The one write in this app whose blast radius is not a single page. Delivery expands a library reference against this row every time it serves, so replacing `tree` re-renders every page that points at the item — published ones included — without any of them being edited, republished or even touched. Nothing warns you first and no revision records it, because the pages did not change; the item did. Changing `label` or `bundle` only moves the item around the picker. Detaching one page from the item, so it keeps a copy of its own, is an editor mutation and not this route.
     *
     * @param id The library item id.
     * @param bundle The block type this item instantiates. Changing it moves the item to a different part of the picker.
     * @param label What the item is called in the picker.
     * @param tree A block and its whole subtree, serialized. Produced by the editor when a selection is made reusable or saved as a template, and instantiated back into real blocks when one is inserted.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesLibraryUpdate(
        id: String,
        bundle: String? = null,
        label: String? = null,
        tree: Any? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/library/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "bundle" to bundle,
            "label" to label,
            "tree" to tree,
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
     * The management view of the menus a tenant keeps — `main`, `footer`, `account` and whatever else the theme asks for, each with the key it is looked up by. This route reads no filter at all — a `?menu_key=` is ignored, which the empty `filter` echo shows — so fetch a page and pick, or address one by id.
     *
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesMenusList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/pages/menus"

        val apiParams = mutableMapOf<String, Any?>(
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
     * Writes a menu by its KEY rather than by its id, which is what makes theme seeding safe to repeat: a key the tenant already has has its label and items replaced in place, a key it does not have is created. `items` is replaced wholesale and never merged, so sending an empty list empties the navigation. One caveat worth reading before you rely on the idempotence: the key's uniqueness is this route's doing and not the database's — `menu_key` carries an index but no unique constraint — so a duplicate key created any other way leaves this route updating whichever row it finds first.
     *
     * @param label What this menu is called for the people who edit it. Required on a create; an update keeps the label it had when this is left out.
     * @param menuKey The stable slot the theme asks for this menu by. Idempotency is keyed on it: sending an existing key replaces that menu instead of creating a second one.
     * @param items The ordered navigation tree. Replaces the stored one completely.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesMenusUpsert(
        label: String,
        menuKey: String,
        items: List<com.revenexx.models.PageMenuItem<Any>>? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/menus"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "label" to label,
            "menuKey" to menuKey,
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
     * Writes the tombstone. The menu drops out of the management list and out of `GET /pages/delivery/menus` in the same moment, so a theme that reads its key gets nothing back and renders nothing — there is no fallback and no error a storefront could act on. The key is free immediately, which means re-seeding the theme is the way back. Check what reads the key before striking it.
     *
     * @param id The menu row id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesMenusDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/menus/{id}"
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
     * One menu and its whole item tree — the ordered links a theme renders as its header, footer or account navigation. `items` is nested, not one level, so this is the entire navigation for that key in a single read. Addressed by ROW ID here; the key a theme knows it by is `menu_key` on the body, and the route that works by key is the upsert.
     *
     * @param id The menu row id — not the menu key.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesMenusGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/menus/{id}"
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
     * The same write as the upsert, for a caller that already holds the row id — use this when editing a menu a person picked from a list, and the upsert when reconciling a theme's defaults. `menu_key` is deliberately not editable here: the key is the handle every theme reads the menu by, so changing it would empty whatever is rendering that key without anything reporting an error.
     *
     * @param id The menu row id.
     * @param items The ordered navigation tree. Replaces the stored one completely.
     * @param label What this menu is called for the people who edit it.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesMenusUpdate(
        id: String,
        items: List<com.revenexx.models.PageMenuItem<Any>>? = null,
        label: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/menus/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "label" to label,
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
     * The EDITORIAL index — every live page of the tenant, whatever its status, newest change first. This is the list the Cockpit shows a person: drafts and archived pages are in it, and a row here says nothing about whether a visitor can see the page, because a published status without a published revision still delivers nothing. A storefront wants `GET /pages/delivery/pages` instead, which answers only what is actually servable. Soft-deleted pages are never returned and the predicate is this route's own, not something a caller can switch off.
     *
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @param bundle Exact page type. The value set belongs to the active theme, so this app constrains it to a non-empty string and nothing more.
     * @param status Exact lifecycle status.
     * @param q Case-insensitive substring search over the page title. Runs in the query, so `page.total` counts the matches. Empty means no search.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesPagesList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        bundle: String? = null,
        status: com.revenexx.enums.PageStatus? = null,
        q: String? = null,
    ): Any {
        val apiPath = "/v1/pages/pages"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "bundle" to bundle,
            "status" to status,
            "q" to q,
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
     * Writes two rows, not one: the page itself and the translation row for its source language, so a page is never without the language it was authored in and `GET /pages/delivery/page?slug=` can match a localized URL from the first moment. Everything the caller leaves out comes from the tenant's settings, not from a literal in this app: `bundle` from default_page_bundle, `sourceLanguage` from default_source_language (resolved for the request's market), and the status of both the page and its source translation from default_page_status (draft | published).
     *
     * @param title What the page is called, in its source language. Shown in the editorial list and searched by `?q=`.
     * @param bundle The page type. Omit to take the default_page_bundle setting.
     * @param hostOptions Page-level blökkli display options as a flat `option key → value` map. Theme-defined; usually left out and set later from the editor.
     * @param meta The page's metadata bag (SEO and social fields). Stored and handed back untouched — this app reads no key of it, so the theme decides what goes in.
     * @param slug The path segment the storefront routes it under, without a leading slash. Unique per tenant among live pages; omit or send null for a page reached only by id. Nothing here derives one from the title.
     * @param sourceLanguage The language you are authoring in, and the fallback for every later translation. Omit to take the default_source_language setting for the request market.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesPagesCreate(
        title: String,
        bundle: String? = null,
        hostOptions: Any? = null,
        meta: Any? = null,
        slug: String? = null,
        sourceLanguage: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/pages"

        val apiParams = mutableMapOf<String, Any?>(
            "bundle" to bundle,
            "hostOptions" to hostOptions,
            "meta" to meta,
            "slug" to slug,
            "sourceLanguage" to sourceLanguage,
            "title" to title,
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
     * Writes a tombstone. The page leaves every list, every read and all delivery at once, and its slug is immediately free for another page — the unique index counts live rows only. Nothing is erased: the translations, blocks, edit state, revisions, comments and preview grants that hang off the page all keep their rows, because their `on delete cascade` belongs to a hard delete and this is not one. So a page can be brought back intact by clearing `deleted_at` — but not through this app, which publishes no route that does it.
     *
     * @param id The page id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesPagesDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/pages/{id}"
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
     * One page RECORD: what it is called, where it routes, what type it is, which revision is live. Not its content — the blocks are not on this row and no expansion here returns them. The editor reads them with `GET /pages/editor/{page_id}/state`, a renderer with `GET /pages/delivery/page`. A soft-deleted page answers 404 exactly like one that never existed, so this is also the check for whether an id is still good.
     *
     * @param id The page id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesPagesGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/pages/{id}"
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
     * Corrects the page RECORD — the five fields an editor changes without opening the visual editor, which are `title`, `slug`, `status`, `meta` and `bundle`, and no others. Anything else in the body is dropped rather than refused, and the block tree is unreachable from here by design: content moves only through the editor's mutation log, so a caller cannot half-edit a page behind the undo history's back. Two consequences worth knowing before you call it: a slug is unique among live pages, so claiming one that is held answers 409; and setting `status` to published does NOT put anything in front of a visitor — delivery needs a revision, which only `POST /pages/editor/{page_id}/publish` writes.
     *
     * @param id The page id.
     * @param bundle The page type. Changing it changes which template the theme renders.
     * @param meta The page's metadata bag. Replaced wholesale, not merged.
     * @param slug The path segment the storefront routes it under. Sending a slug another live page holds answers 409; sending null makes the page unreachable by path.
     * @param status The lifecycle status. Setting `published` here does NOT publish content — delivery still needs a revision, which only `POST /pages/editor/{page_id}/publish` writes.
     * @param title The page title in its source language.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesPagesUpdate(
        id: String,
        bundle: String? = null,
        meta: Any? = null,
        slug: String? = null,
        status: com.revenexx.enums.PageStatus? = null,
        title: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/pages/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "bundle" to bundle,
            "meta" to meta,
            "slug" to slug,
            "status" to status,
            "title" to title,
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
     * One entry per publication, newest first, which is the order a history is read in and the one this route sorts by unless `order` says otherwise. The `snapshot` — the whole published page, in every language — is deliberately not in the index: it is page-sized, and nothing that renders a history needs it.
     *
     * @param id The page whose history to read.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @param label Exact revision label — the name a publication was made under. An equality, not a search.
     * @param createdBy Exact user id of whoever published.
     * @param createdByName Exact display name recorded at publish time.
     * @param createdAt Exact publication timestamp, RFC 3339. Equality only — this data plane has no range operator, so walk the history with `order=created_at.desc` and `limit` instead.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesPagesRevisions(
        id: String,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        label: String? = null,
        createdBy: String? = null,
        createdByName: String? = null,
        createdAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/pages/{id}/revisions"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "label" to label,
            "created_by" to createdBy,
            "created_by_name" to createdByName,
            "created_at" to createdAt,
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
     * The target of a theme activation hook: hand it the theme's default pages and menus and it creates whatever is missing. Idempotent by `slug` and by menu key — a slug or a key the tenant already holds is skipped rather than rewritten, so re-running after a theme update adds only the new ones and never overwrites what an editor has since changed. A seeded page is published on the spot, immediately servable by delivery: the default_page_status setting deliberately does not apply, because a theme that activates with invisible pages looks broken.
     *
     * @param menus The menus to create. One with no key or no label is reported under `skipped`.
     * @param pages The pages to create. One that has no `slug` or no `title` is reported under `skipped` rather than refused, so one bad entry never loses the rest.
     * @return [com.revenexx.models.SeedResult]
     */
    @JvmOverloads
    suspend fun pagesSeed(
        menus: List<Any>? = null,
        pages: List<Any>? = null,
    ): com.revenexx.models.SeedResult {
        val apiPath = "/v1/pages/seed"

        val apiParams = mutableMapOf<String, Any?>(
            "menus" to menus,
            "pages" to pages,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.SeedResult = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.SeedResult.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.SeedResult::class.java,
            converter,
        )
    }


    /**
     * Every column of a template is an exact-match filter here: `?page_bundle=standard&field_name=content` is how a picker asks for the templates offered in one place, and `?is_default=true` is how a "new page" flow finds the one to start from.
     *
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. A column this entity does not have, or any other shape, is refused with 400.
     * @param id Exact template id.
     * @param label Exact label. An equality, not a search — there is no substring search on this route.
     * @param description Exact description text. An equality, so it is the round-trip of the value a picker already showed, not a search.
     * @param pageBundle Exact page type the template is offered on. A template offered everywhere has no page_bundle and is not returned by this filter.
     * @param fieldName Exact field the template is offered in.
     * @param isDefault Whether the template is the starting point for new pages of its bundle.
     * @param createdBy Exact user id of whoever saved the template.
     * @param createdAt Exact creation timestamp, RFC 3339. Equality only — there is no range operator here, so walk the list with `order` instead.
     * @param updatedAt Exact last-change timestamp, RFC 3339. Equality only.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesTemplatesList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        id: String? = null,
        label: String? = null,
        description: String? = null,
        pageBundle: String? = null,
        fieldName: String? = null,
        isDefault: Boolean? = null,
        createdBy: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
    ): Any {
        val apiPath = "/v1/pages/templates"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "id" to id,
            "label" to label,
            "description" to description,
            "page_bundle" to pageBundle,
            "field_name" to fieldName,
            "is_default" to isDefault,
            "created_by" to createdBy,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
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
     * Removes the template row outright. This is the one delete in the app that is not a tombstone — `templates` carries no `deleted_at` — so it cannot be undone and the id will not come back. Nothing else breaks by it: pages built from the template hold their own copy of the blocks and never referenced the row.
     *
     * @param id The template id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesTemplatesDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/templates/{id}"
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
     * The blocks a page would START from if an editor picked this template — read it to preview the insert. A template is a COPY source, the opposite of a library item: nothing links back from the pages already built from it, so this tells you what future pages get and nothing about existing ones.
     *
     * @param id The template id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesTemplatesGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/templates/{id}"
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
     * Edits what a future page will start from. Because templates copy rather than share, this reaches nothing that already exists — pages built from it keep the blocks they were handed, which is exactly the property that makes a template safe to edit and a library item dangerous. `is_default` is the one field with an effect past the picker: it decides what a new page of `page_bundle` starts with, and nothing here stops two templates of the same bundle from both claiming it, so which one wins is left to whoever reads the list.
     *
     * @param id The template id.
     * @param description A sentence about when to reach for it, shown next to the label.
     * @param fieldName The field this template is offered in. Null offers it in every field.
     * @param isDefault Whether a new page of this bundle starts from this template.
     * @param label What the template is called in the picker.
     * @param pageBundle The page type this template is offered on. Null offers it on every page type.
     * @param tree The blocks the template inserts, in order. Replaces the stored tree completely.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesTemplatesUpdate(
        id: String,
        description: String? = null,
        fieldName: String? = null,
        isDefault: Boolean? = null,
        label: String? = null,
        pageBundle: String? = null,
        tree: List<com.revenexx.models.PageBlockTree>? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/templates/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "field_name" to fieldName,
            "is_default" to isDefault,
            "label" to label,
            "page_bundle" to pageBundle,
            "tree" to tree,
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
     * Discovery for the vocabulary routes: the enums this app publishes, each with its name, its title and what it is for, and none of them unpacked — the permitted values are not on this route, only on the one that serves a single vocabulary. Names: edit-state-statuses, page-statuses, translation-statuses. Fetch one with GET /pages/vocabularies/{name}; a client holding the qualified pair 'pages.<name>' builds that URL from the pair alone.
     *
     * @return [com.revenexx.models.PagesVocabularyIndex]
     */
    suspend fun pagesVocabulariesList(
    ): com.revenexx.models.PagesVocabularyIndex {
        val apiPath = "/v1/pages/vocabularies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PagesVocabularyIndex = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PagesVocabularyIndex.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PagesVocabularyIndex::class.java,
            converter,
        )
    }


    /**
     * One vocabulary unpacked: every value the column permits, each with the title to show for it, the sentence explaining it and the badge tone to render it in — everything a select or a status pill needs, so nothing downstream keeps its own copy of the labels. The values are read out of the column's CHECK constraint, so the served set IS the enforced set and the two cannot drift — a value added to the constraint appears here even before anyone labels it, titled from its own key. Values come back in constraint order, which is the order a select should offer. 'closed' says the set is exhaustive, so a value outside it is stale data rather than a missing label. Names: edit-state-statuses, page-statuses, translation-statuses.
     *
     * @param name The vocabulary name — the part after the dot in the qualified id.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesVocabulariesGet(
        name: com.revenexx.enums.PagesVocabulariesGetName,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/vocabularies/{name}"
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