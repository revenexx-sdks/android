```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Storage

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val storage = Storage(client)

val result = storage.syncRuleUpdate(
    id = "", 
    enabled = true, // (optional)
    options = listOf(), // (optional)
    schedule = "0 3 * * *", // (optional)
    sftp_account_id = "", // (optional)
    source_path = "/uploads", // (optional)
    target_folder_id = "", // (optional)
)
```
