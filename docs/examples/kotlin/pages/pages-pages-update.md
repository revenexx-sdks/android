```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Pages
import com.revenexx.enums.PageStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pages = Pages(client)

val result = pages.pagesPagesUpdate(
    id = "", 
    bundle = "standard", // (optional)
    meta = mapOf( "a" to "b" ), // (optional)
    slug = "about-us", // (optional)
    status = PageStatus.DRAFT, // (optional)
    title = "About us", // (optional)
)
```
