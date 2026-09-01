```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Pages

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pages = Pages(client)

val result = pages.pagesTemplatesUpdate(
    id = "", 
    description = "Full-width hero followed by a two-column teaser row.", // (optional)
    field_name = "content", // (optional)
    is_default = true, // (optional)
    label = "Hero with two teasers", // (optional)
    page_bundle = "standard", // (optional)
    tree = listOf(), // (optional)
)
```
