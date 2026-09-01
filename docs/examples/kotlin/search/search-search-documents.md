```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Search
import com.revenexx.enums.Collection

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val search = Search(client)

val result = search.searchSearchDocuments(
    collection = collection.PRODUCTS,
    exclude_fields = "", // (optional)
    facet_by = "", // (optional)
    filter_by = "", // (optional)
    group_by = "", // (optional)
    highlight_full_fields = "", // (optional)
    include_fields = "", // (optional)
    max_facet_values = 1, // (optional)
    num_typos = 1, // (optional)
    page = 1, // (optional)
    per_page = 1, // (optional)
    prefix = "", // (optional)
    q = "", // (optional)
    query_by = "", // (optional)
    sort_by = "", // (optional)
)
```
