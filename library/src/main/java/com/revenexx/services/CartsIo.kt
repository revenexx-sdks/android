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
 * Moving carts in and out as JSON or CSV — the bulk data plane, which a storefront checkout never touches. An import/export profile (Baseline-IO-compatible) declares which direction it runs in, whether it carries whole carts or bare lines, the format, how the external columns are named, and what an import does with the lines a target cart already has; four templates ship with the app and are seeded idempotently by name. The two routes that actually move data are here as well: export one cart through an export profile or ad hoc, and import a payload into a new cart or an existing one. A profile only ever runs in the direction it declares — handing an import profile to the export route is a 400.
 */
class CartsIo(client: Client) : Service(client) {

    /**
     * Reads a payload of lines into a cart — the bulk-order path a buyer pastes a spreadsheet into. With `target_cart_id` the lines land in that cart, which must be active, and the profile's `apply_mode` decides what happens to the lines already there: 'replace' clears them first, 'insert' and 'append' both add. Without a target a new cart is created, and an OWNER is then required — `contact_id` or `session_key` — because a cart with neither cannot exist. `profile_id` names an IMPORT profile; without one the payload is read ad hoc, as CSV when `csv` is present and as JSON otherwise. The lines fold into identical product lines exactly as carts.items.create does, so `imported_lines` counts the lines READ and the cart may have gained fewer rows than that. A payload that parses to no line at all is a 400 rather than a quiet no-op.
     *
     * @param contactId Owner of the cart this import creates. Ignored when target_cart_id is sent.
     * @param csv The CSV rows, when that is easier than putting them in `payload`. First line is the header, and its names are the ones the profile's mapping expects (the bundled quick-order template reads sku, name, quantity, unit_price). Numbers are coerced; a JSON column survives as a JSON string.
     * @param name Name for the cart this import creates. A name in the payload's own `cart` block wins over it; without either the cart is called 'Imported cart'.
     * @param payload The import itself. As an object: `{ "cart": { name, status, currency, channel_id, metadata }, "items": [ … ] }` — the same document carts.export produces, so an export round-trips. As a string: that document as raw JSON, or CSV rows when the profile is a csv one. A line with neither `name` nor `sku` is dropped, and a payload that leaves no line at all is a 400.
     * @param profileId The import profile to run — one of the ids `GET /carts/io/profiles?direction=import` lists. Omit it for an ad-hoc import: the payload is then read in the canonical shape, and as CSV if `csv` is what carried it.
     * @param sessionKey Guest owner of the cart this import creates — the storefront's own session key. Ignored when target_cart_id is sent.
     * @param targetCartId An existing ACTIVE cart to import into. The lines are added to it (merging identical product lines), unless the profile says `apply_mode: replace`, which clears it first. Without this a new cart is created and an owner is required.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsImport(
        contactId: String? = null,
        csv: String? = null,
        name: String? = null,
        payload: Any? = null,
        profileId: String? = null,
        sessionKey: String? = null,
        targetCartId: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/import"

        val apiParams = mutableMapOf<String, Any?>(
            "contact_id" to contactId,
            "csv" to csv,
            "name" to name,
            "payload" to payload,
            "profile_id" to profileId,
            "session_key" to sessionKey,
            "target_cart_id" to targetCartId,
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
     * The filters are what make this list usable: `?direction=export` is how a client offers the profiles that carts.export will accept, and `?is_template=true` separates the four bundled templates from what a merchant wrote. An unknown column is dropped rather than refused — `filter` echoes what was understood.
     *
     * @param id One profile, in list form.
     * @param name Exact profile name — how the bundled templates are addressed, since they are identified by name.
     * @param direction Import or export profiles. `?direction=export` is how a client offers exactly the profiles carts.export will accept — the other half is a 400.
     * @param entity Profiles that carry whole carts, or profiles that carry lines.
     * @param format JSON profiles or CSV profiles.
     * @param applyMode Profiles that replace a target cart's lines, as against those that add to them.
     * @param isTemplate The four bundled templates, or everything a merchant wrote.
     * @param createdAt Exact instant, not a range.
     * @param updatedAt Exact instant, not a range.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsIoProfilesList(
        id: String? = null,
        name: String? = null,
        direction: com.revenexx.enums.CartIoDirection? = null,
        entity: com.revenexx.enums.CartIoEntity? = null,
        format: com.revenexx.enums.CartIoFormat? = null,
        applyMode: com.revenexx.enums.CartIoApplyMode? = null,
        isTemplate: Boolean? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/io/profiles"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "name" to name,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "apply_mode" to applyMode,
            "is_template" to isTemplate,
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
     * Defines a new import/export profile. Two fields are required and have no default — `name`, which must be unique within the tenant, and `direction`, which fixes the one way this profile will ever run. Everything else defaults to the common case: whole carts, JSON, `apply_mode` 'insert', not a template. The uniqueness of the name is a unique index rather than a check in this app, so a reused name is a 409 no matter which route wrote the other one, including the four bundled templates. The shape is Baseline-IO-compatible, so a mapping written for another app's import reads the same way here. Creating a profile does not move any data: carts.export and carts.import are what execute one, and each refuses a profile pointed the wrong way.
     *
     * @param direction Which way this profile runs. A profile only ever runs in the direction it declares: handing an import profile to carts.export is a 400, and the other way round.
     * @param name What a merchant picks this profile by. Unique within the tenant — reusing a name is a 409.
     * @param applyMode What an import does with the lines the target cart already has: 'replace' clears them first, 'insert' and 'append' both add and behave identically today. Read only when the import names a target_cart_id. Default 'insert'.
     * @param entity What the profile carries: whole carts (the `{cart, items}` document) or bare cart lines. Default 'carts'.
     * @param format The wire format. 'json' is the canonical, re-importable document; 'csv' is the spreadsheet form, and only line fields survive it. Default 'json'.
     * @param isTemplate One of the bundled templates. Set by carts.io.profiles.defaults; a profile a merchant writes is not one.
     * @param mapping Baseline-IO-compatible column mapping. An empty object (or null) is identity: the full canonical shape, every field under its own name.
     * @param options Free-form options carried with the profile. The four bundled templates put one human sentence under `description` and nothing else; no other key is read by this app, so anything a merchant needs alongside a profile can live here.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsIoProfilesCreate(
        direction: com.revenexx.enums.CartIoDirection,
        name: String,
        applyMode: com.revenexx.enums.CartIoApplyMode? = null,
        entity: com.revenexx.enums.CartIoEntity? = null,
        format: com.revenexx.enums.CartIoFormat? = null,
        isTemplate: Boolean? = null,
        mapping: Any? = null,
        options: Any? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/io/profiles"

        val apiParams = mutableMapOf<String, Any?>(
            "apply_mode" to applyMode,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "is_template" to isTemplate,
            "mapping" to mapping,
            "name" to name,
            "options" to options,
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
     * Seeds the 4 bundled templates and reports which of them it had to create — the call that gives a fresh tenant something to export through before anybody has written a profile. Idempotent and matched by NAME, so a second call answers with everything under 'existing' and writes nothing, and a template a merchant has edited is left exactly as they left it rather than reset. It also runs by itself on app.installed; call it by hand where that event cannot be relied on, and after deleting a template to get it back.
     *
     * @return [Any]
     */
    suspend fun cartsIoProfilesDefaults(
    ): Any {
        val apiPath = "/v1/carts/io/profiles/defaults"

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
     * Removes a profile. Nothing in this app points at one — no cart and no line stores the profile it was imported through — so no foreign key holds the delete up and nothing is orphaned by it; what breaks is the caller still holding that `profile_id`, which answers 404 on its next run. Deleting one of the four bundled templates is not permanent either: the next carts.io.profiles.defaults, and the next install of this app, seeds it again by name, in the shape it ships with rather than the shape a merchant had edited it into.
     *
     * @param id The import/export profile, by its id — one of the ids `GET /carts/io/profiles` lists.
     * @return [com.revenexx.models.Error]
     */
    suspend fun cartsIoProfilesDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/io/profiles/{id}"
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
     * One profile by id — the id carts.export and carts.import name in `profile_id`. Read it to see what a run will do before starting one: `direction`, because a profile only ever runs the way it declares; `entity`, whole carts or bare lines; `format`, where json round-trips and csv carries line fields only; `mapping`, what the external columns are called; and `apply_mode`, which decides what an import does with the lines a target cart already has. `is_template` says whether this is one of the four the app ships with or something a merchant wrote. Reading a profile runs nothing and changes nothing.
     *
     * @param id The import/export profile, by its id — one of the ids `GET /carts/io/profiles` lists.
     * @return [com.revenexx.models.Error]
     */
    suspend fun cartsIoProfilesGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/io/profiles/{id}"
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
     * Edits a profile in place, the four bundled templates included — seeding matches on name and never rewrites what it finds, so an edit made here survives every later call to carts.io.profiles.defaults and every reinstall of the app. The name stays unique in the tenant, so renaming onto another profile's name is a 409, and a payload carrying no updatable field answers 400 rather than storing nothing quietly. Runs that already happened are unaffected: a profile is read at the moment carts.export or carts.import executes and nothing is kept pointing back at it, so changing a mapping changes the next run and no earlier one.
     *
     * @param id The import/export profile, by its id — one of the ids `GET /carts/io/profiles` lists.
     * @param applyMode What an import does with the lines the target cart already has: 'replace' clears them first, 'insert' and 'append' both add and behave identically today. Read only when the import names a target_cart_id. Default 'insert'.
     * @param direction Which way this profile runs. A profile only ever runs in the direction it declares: handing an import profile to carts.export is a 400, and the other way round.
     * @param entity What the profile carries: whole carts (the `{cart, items}` document) or bare cart lines. Default 'carts'.
     * @param format The wire format. 'json' is the canonical, re-importable document; 'csv' is the spreadsheet form, and only line fields survive it. Default 'json'.
     * @param isTemplate One of the bundled templates. Set by carts.io.profiles.defaults; a profile a merchant writes is not one.
     * @param mapping Baseline-IO-compatible column mapping. An empty object (or null) is identity: the full canonical shape, every field under its own name.
     * @param name What a merchant picks this profile by. Unique within the tenant — reusing a name is a 409.
     * @param options Free-form options carried with the profile. The four bundled templates put one human sentence under `description` and nothing else; no other key is read by this app, so anything a merchant needs alongside a profile can live here.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsIoProfilesUpdate(
        id: String,
        applyMode: com.revenexx.enums.CartIoApplyMode? = null,
        direction: com.revenexx.enums.CartIoDirection? = null,
        entity: com.revenexx.enums.CartIoEntity? = null,
        format: com.revenexx.enums.CartIoFormat? = null,
        isTemplate: Boolean? = null,
        mapping: Any? = null,
        name: String? = null,
        options: Any? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/io/profiles/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "apply_mode" to applyMode,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "is_template" to isTemplate,
            "mapping" to mapping,
            "name" to name,
            "options" to options,
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
     * Renders one cart as a document somebody can take away. With `profile_id` the named EXPORT profile decides the format, the entity and the column names; handing it an import profile is a 400, because a profile only runs the way it declares. Without one the call runs ad hoc — JSON, unless `format: 'csv'` says otherwise. The JSON form is `{cart: {…}, items: […]}` and is exactly what carts.import takes back, so an export round-trips; the CSV form is the lines only, header first, and drops everything that lives on the cart rather than on a line. Nothing is stored and nothing about the cart changes — `filename` is a suggestion for a browser download, not a file this app keeps — and a cart of any status can be exported, including one already ordered.
     *
     * @param id The cart, by its id — the `id` every cart answer carries. A uuid: the data plane casts the segment, so a code or a slug is refused before the cart is looked up.
     * @param format Format of an ad-hoc export, read only when no profile_id is sent. 'json' returns the whole `{cart, items}` document, 'csv' the lines alone. Default 'json'.
     * @param profileId The export profile to run — one of the ids `GET /carts/io/profiles?direction=export` lists. Omit it for an ad-hoc export in the canonical shape, which is what `format` is for.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsExport(
        id: String,
        format: com.revenexx.enums.CartExportFormat? = null,
        profileId: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{id}/export"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "format" to format,
            "profile_id" to profileId,
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


}