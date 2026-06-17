```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Storage
import com.revenexx.enums.Visibility

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val storage = Storage(client)

val result = storage.assetUpdate(
    id = "", 
    alt_text = "", // (optional)
    description = "", // (optional)
    display_name = "", // (optional)
    folder_id = "", // (optional)
    name = "", // (optional)
    tags = listOf(), // (optional)
    visibility = visibility.PUBLIC, // (optional)
)
```
