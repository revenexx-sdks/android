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
    url = "", 
    headers = mapOf( "a" to "b" ), // (optional)
    viewportWidth = 0, // (optional)
    viewportHeight = 0, // (optional)
    scale = 0, // (optional)
    theme = theme.LIGHT, // (optional)
    userAgent = "", // (optional)
    fullpage = false, // (optional)
    locale = "", // (optional)
    timezone = timezone.AFRICA_ABIDJAN, // (optional)
    latitude = 0, // (optional)
    longitude = 0, // (optional)
    accuracy = 0, // (optional)
    touch = false, // (optional)
    permissions = permissions.GEOLOCATION, // (optional)
    sleep = 0, // (optional)
    width = 0, // (optional)
    height = 0, // (optional)
    quality = 0, // (optional)
    output = output.JPG, // (optional)
)
```
