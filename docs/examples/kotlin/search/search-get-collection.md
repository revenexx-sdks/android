```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Search
import com.revenexx.enums.Collection

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val search = Search(client)

val result = search.searchGetCollection(
    collection = collection.PRODUCTS,
)
```
