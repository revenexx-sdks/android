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
 * Generated images: initials, QR codes, country flags, browser and credit-card icons. Every operation answers image bytes, not JSON.
 */
class Avatars(client: Client) : Service(client) {

    /**
     * You can use this endpoint to show different browser icons to your users. The code argument receives the browser code as it appears in your user [GET /account/sessions](https://app.revenexx.com/docs/references/cloud/client-web/account#getSessions) endpoint. Use width, height and quality arguments to change the output settings.
     * 
     * When one dimension is specified and the other is 0, the image is scaled with preserved aspect ratio. If both dimensions are 0, the API provides an image at source quality. If dimensions are not specified, the default size of image returned is 100x100px.
     *
     * @param code Browser Code.
     * @param width Image width. Pass an integer between 0 to 2000. Defaults to 100.
     * @param height Image height. Pass an integer between 0 to 2000. Defaults to 100.
     * @param quality Image quality. Pass an integer between 0 to 100. Defaults to keep existing image quality.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetBrowser(
        code: com.revenexx.enums.Code,
        width: Long? = null,
        height: Long? = null,
        quality: Long? = null,
    ): Any {
        val apiPath = "/v1/avatars/browsers/{code}"
            .replace("{code}", code.value)

        val apiParams = mutableMapOf<String, Any?>(
            "width" to width,
            "height" to height,
            "quality" to quality,
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
     * The credit card endpoint will return you the icon of the credit card provider you need. Use width, height and quality arguments to change the output settings.
     * 
     * When one dimension is specified and the other is 0, the image is scaled with preserved aspect ratio. If both dimensions are 0, the API provides an image at source quality. If dimensions are not specified, the default size of image returned is 100x100px.
     * 
     *
     * @param code Credit Card Code. Possible values: amex, argencard, cabal, cencosud, diners, discover, elo, hipercard, jcb, mastercard, naranja, targeta-shopping, unionpay, visa, mir, maestro, rupay.
     * @param width Image width. Pass an integer between 0 to 2000. Defaults to 100.
     * @param height Image height. Pass an integer between 0 to 2000. Defaults to 100.
     * @param quality Image quality. Pass an integer between 0 to 100. Defaults to keep existing image quality.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetCreditCard(
        code: com.revenexx.enums.AvatarsGetCreditCardCode,
        width: Long? = null,
        height: Long? = null,
        quality: Long? = null,
    ): Any {
        val apiPath = "/v1/avatars/credit-cards/{code}"
            .replace("{code}", code.value)

        val apiParams = mutableMapOf<String, Any?>(
            "width" to width,
            "height" to height,
            "quality" to quality,
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
     * You can use this endpoint to show different country flags icons to your users. The code argument receives the 2 letter country code. Use width, height and quality arguments to change the output settings. Country codes follow the [ISO 3166-1](https://en.wikipedia.org/wiki/ISO_3166-1) standard.
     * 
     * When one dimension is specified and the other is 0, the image is scaled with preserved aspect ratio. If both dimensions are 0, the API provides an image at source quality. If dimensions are not specified, the default size of image returned is 100x100px.
     * 
     *
     * @param code Country Code. ISO Alpha-2 country code format.
     * @param width Image width. Pass an integer between 0 to 2000. Defaults to 100.
     * @param height Image height. Pass an integer between 0 to 2000. Defaults to 100.
     * @param quality Image quality. Pass an integer between 0 to 100. Defaults to keep existing image quality.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetFlag(
        code: com.revenexx.enums.AvatarsGetFlagCode,
        width: Long? = null,
        height: Long? = null,
        quality: Long? = null,
    ): Any {
        val apiPath = "/v1/avatars/flags/{code}"
            .replace("{code}", code.value)

        val apiParams = mutableMapOf<String, Any?>(
            "width" to width,
            "height" to height,
            "quality" to quality,
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
     * Use this endpoint to fetch a remote image URL and crop it to any image size you want. This endpoint is very useful if you need to crop and display remote images in your app or in case you want to make sure a 3rd party image is properly served using a TLS protocol.
     * 
     * When one dimension is specified and the other is 0, the image is scaled with preserved aspect ratio. If both dimensions are 0, the API provides an image at source quality. If dimensions are not specified, the default size of image returned is 400x400px.
     * 
     * This endpoint does not follow HTTP redirects.
     *
     * @param url Image URL which you want to crop. Must be publicly reachable and answer 200 with a raster image on the first request: the fetch does not follow redirects, so a URL that 301s answers 404, as does any URL whose response is not 200. The bytes are decoded by Imagick — an SVG (including most `favicon.ico` files, which are SVG in disguise) and a genuine `.ico` both fail to decode and answer 500.
     * @param width Resize preview image width, Pass an integer between 0 to 2000. Defaults to 400.
     * @param height Resize preview image height, Pass an integer between 0 to 2000. Defaults to 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetImage(
        url: String,
        width: Long? = null,
        height: Long? = null,
    ): Any {
        val apiPath = "/v1/avatars/image"

        val apiParams = mutableMapOf<String, Any?>(
            "url" to url,
            "width" to width,
            "height" to height,
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
     * Use this endpoint to show your user initials avatar icon on your website or app. By default, this route will try to print your logged-in user name or email initials. You can also overwrite the user name if you pass the 'name' parameter. If no name is given and no user is logged, an empty avatar will be returned.
     * 
     * You can use the color and background params to change the avatar colors. By default, a random theme will be selected. The random theme will persist for the user's initials when reloading the same theme will always return for the same initials.
     * 
     * When one dimension is specified and the other is 0, the image is scaled with preserved aspect ratio. If both dimensions are 0, the API provides an image at source quality. If dimensions are not specified, the default size of image returned is 100x100px.
     * 
     *
     * @param name Full Name. When empty, current user name or email will be used. Max length: 128 chars.
     * @param width Image width. Pass an integer between 0 to 2000. Defaults to 100.
     * @param height Image height. Pass an integer between 0 to 2000. Defaults to 100.
     * @param background Changes background color. By default a random color will be picked and stay will persistent to the given name.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetInitials(
        name: String? = null,
        width: Long? = null,
        height: Long? = null,
        background: String? = null,
    ): Any {
        val apiPath = "/v1/avatars/initials"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "width" to width,
            "height" to height,
            "background" to background,
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
     * Converts a given plain text to a QR code image. You can use the query parameters to change the size and style of the resulting image.
     * 
     *
     * @param text Plain text to be converted to QR code image.
     * @param size QR code size. Pass an integer between 1 to 1000. Defaults to 400.
     * @param margin Margin from edge. Pass an integer between 0 to 10. Defaults to 1.
     * @param download Return resulting image with 'Content-Disposition: attachment ' headers for the browser to start downloading it. Pass 0 for no header, or 1 for otherwise. Default value is set to 0.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetQR(
        text: String,
        size: Long? = null,
        margin: Long? = null,
        download: Boolean? = null,
    ): Any {
        val apiPath = "/v1/avatars/qr"

        val apiParams = mutableMapOf<String, Any?>(
            "text" to text,
            "size" to size,
            "margin" to margin,
            "download" to download,
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
     * Use this endpoint to capture a screenshot of any website URL. This endpoint uses a headless browser to render the webpage and capture it as an image.
     * 
     * You can configure the browser viewport size, theme, user agent, geolocation, permissions, and more. Capture either just the viewport or the full page scroll.
     * 
     * When width and height are specified, the image is resized accordingly. If both dimensions are 0, the API provides an image at original size. If dimensions are not specified, the default viewport size is 1280x720px.
     *
     * @param url Website URL which you want to capture.
     * @param headers HTTP headers to send with the browser request. Defaults to empty.
     * @param viewportWidth Browser viewport width. Pass an integer between 1 to 1920. Defaults to 1280.
     * @param viewportHeight Browser viewport height. Pass an integer between 1 to 1080. Defaults to 720.
     * @param scale Browser scale factor. Pass a number between 0.1 to 3. Defaults to 1.
     * @param theme Browser theme. Pass "light" or "dark". Defaults to "light".
     * @param userAgent Custom user agent string. Defaults to browser default.
     * @param fullpage Capture full page scroll. Pass 0 for viewport only, or 1 for full page. Defaults to 0.
     * @param locale Browser locale (e.g., "en-US", "fr-FR"). Defaults to browser default.
     * @param timezone IANA timezone identifier, canonically cased (e.g. `America/New_York`, `Europe/London`). Defaults to the browser default. Only two-segment identifiers are accepted: `UTC` and the three-segment ids such as `America/Argentina/Buenos_Aires` are refused with 404.
     * @param latitude Geolocation latitude. Pass a number between -90 to 90. Defaults to 0.
     * @param longitude Geolocation longitude. Pass a number between -180 to 180. Defaults to 0.
     * @param accuracy Geolocation accuracy in meters. Pass a number between 0 to 100000. Defaults to 0.
     * @param touch Enable touch support. Pass 0 for no touch, or 1 for touch enabled. Defaults to 0.
     * @param permissions Browser permissions to grant. Pass an array of permission names like ["geolocation", "camera", "microphone"]. Defaults to empty.
     * @param sleep Wait time in seconds before taking the screenshot. Pass an integer between 0 to 10. Defaults to 0.
     * @param width Output image width. Pass 0 to use original width, or an integer between 1 to 2000. Defaults to 0 (original width).
     * @param height Output image height. Pass 0 to use original height, or an integer between 1 to 2000. Defaults to 0 (original height).
     * @param quality Screenshot quality. Pass an integer between 0 to 100. Defaults to keep existing image quality.
     * @param output Output format type (jpeg, jpg, png, gif and webp).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun avatarsGetScreenshot(
        url: String,
        headers: Any? = null,
        viewportWidth: Long? = null,
        viewportHeight: Long? = null,
        scale: Double? = null,
        theme: com.revenexx.enums.Theme? = null,
        userAgent: String? = null,
        fullpage: Boolean? = null,
        locale: String? = null,
        timezone: com.revenexx.enums.Timezone? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        accuracy: Double? = null,
        touch: Boolean? = null,
        permissions: List<com.revenexx.enums.Permissions>? = null,
        sleep: Long? = null,
        width: Long? = null,
        height: Long? = null,
        quality: Long? = null,
        output: com.revenexx.enums.Output? = null,
    ): Any {
        val apiPath = "/v1/avatars/screenshots"

        val apiParams = mutableMapOf<String, Any?>(
            "url" to url,
            "headers" to headers,
            "viewportWidth" to viewportWidth,
            "viewportHeight" to viewportHeight,
            "scale" to scale,
            "theme" to theme,
            "userAgent" to userAgent,
            "fullpage" to fullpage,
            "locale" to locale,
            "timezone" to timezone,
            "latitude" to latitude,
            "longitude" to longitude,
            "accuracy" to accuracy,
            "touch" to touch,
            "permissions" to permissions,
            "sleep" to sleep,
            "width" to width,
            "height" to height,
            "quality" to quality,
            "output" to output,
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


}