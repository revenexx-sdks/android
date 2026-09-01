```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Pages

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val pages = Pages(client)

val result = pages.pagesPagesRevisions(
    id = "", 
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    label = "Autumn campaign", // (optional)
    created_by = "", // (optional)
    created_by_name = "", // (optional)
    created_at = "", // (optional)
)
```
