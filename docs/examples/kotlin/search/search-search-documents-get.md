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
    collection = collection.PRODUCTS,
    q = "", // (optional)
    query_by = "", // (optional)
    filter_by = "", // (optional)
    sort_by = "", // (optional)
    facet_by = "", // (optional)
    max_facet_values = 1, // (optional)
    group_by = "", // (optional)
    include_fields = "", // (optional)
    exclude_fields = "", // (optional)
    highlight_full_fields = "", // (optional)
    num_typos = 1, // (optional)
    prefix = "", // (optional)
    page = 1, // (optional)
    per_page = 1, // (optional)
)
```
