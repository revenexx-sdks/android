```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Io
import com.revenexx.enums.Format

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val io = Io(client)

val result = io.createExport(
    app = "", 
    entity = "", 
    vendor = "", 
    format = format.CSV, // (optional)
    profile_id = "", // (optional)
)
```
