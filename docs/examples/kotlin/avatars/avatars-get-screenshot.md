```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Avatars
import com.revenexx.enums.Theme
import com.revenexx.enums.Timezone
import com.revenexx.enums.Permissions
import com.revenexx.enums.Output

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val avatars = Avatars(client)

val result = avatars.avatarsGetScreenshot(
    url = "https://example.com", 
    headers = mapOf( "a" to "b" ), // (optional)
    viewportWidth = 1, // (optional)
    viewportHeight = 1, // (optional)
    scale = 1, // (optional)
    theme = theme.LIGHT, // (optional)
    userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15", // (optional)
    fullpage = true, // (optional)
    locale = "en-US", // (optional)
    timezone = timezone.AFRICA_ABIDJAN, // (optional)
    latitude = 9.99, // (optional)
    longitude = 9.99, // (optional)
    accuracy = 9.99, // (optional)
    touch = true, // (optional)
    permissions = permissions.GEOLOCATION, // (optional)
    sleep = 1, // (optional)
    width = 1, // (optional)
    height = 1, // (optional)
    quality = 1, // (optional)
    output = output.JPG, // (optional)
)
```
