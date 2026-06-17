```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts
import com.revenexx.enums.CartExportFormat

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsExport(
    id = "", 
    format = CartExportFormat.JSON, // (optional)
    profile_id = "", // (optional)
)
```
