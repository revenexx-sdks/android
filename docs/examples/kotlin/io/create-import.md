```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Io
import com.revenexx.enums.Format
import com.revenexx.enums.Mode
import com.revenexx.enums.CreateImportTarget

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val io = Io(client)

val result = io.createImport(
    app = "", 
    entity = "", 
    object_key = "", 
    vendor = "", 
    format = format.CSV, // (optional)
    keys = listOf(), // (optional)
    max_rejects = 1, // (optional)
    mode = mode.UPSERT, // (optional)
    profile_id = "", // (optional)
    target = CreateImportTarget.LIVE, // (optional)
)
```
