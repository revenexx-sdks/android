```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Io
import com.revenexx.enums.Direction
import com.revenexx.enums.ApplyMode

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val io = Io(client)

val result = io.updateProfile(
    id = "", 
    app = "", 
    direction = direction.IMPORT,
    entity = "", 
    format = "", 
    name = "", 
    vendor = "", 
    apply_mode = apply_mode.UPSERT, // (optional)
    mapping = mapOf( "a" to "b" ), // (optional)
    markets = listOf(), // (optional)
    options = mapOf( "a" to "b" ), // (optional)
)
```
