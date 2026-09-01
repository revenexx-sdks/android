```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Pages

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pages = Pages(client)

val result = pages.pagesTemplatesList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    label = "Hero with two teasers", // (optional)
    description = "Full-width hero followed by a two-column teaser row.", // (optional)
    page_bundle = "standard", // (optional)
    field_name = "content", // (optional)
    is_default = true, // (optional)
    created_by = "", // (optional)
    created_at = "", // (optional)
    updated_at = "", // (optional)
)
```
