```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Search
import com.revenexx.enums.Collection

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val search = Search(client)

val result = search.searchSearchDocumentsGet(
    collection = collection.GREETINGS,
    q = "", // (optional)
    query_by = "", // (optional)
    filter_by = "", // (optional)
    sort_by = "", // (optional)
    page = 0, // (optional)
    per_page = 0, // (optional)
)
```
