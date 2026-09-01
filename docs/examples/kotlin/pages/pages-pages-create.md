```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Pages

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pages = Pages(client)

val result = pages.pagesPagesCreate(
    title = "About us", 
    bundle = "standard", // (optional)
    hostOptions = mapOf( "a" to "b" ), // (optional)
    meta = mapOf( "a" to "b" ), // (optional)
    slug = "about-us", // (optional)
    sourceLanguage = "de", // (optional)
)
```
