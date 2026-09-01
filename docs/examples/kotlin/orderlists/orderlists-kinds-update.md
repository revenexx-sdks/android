```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orderlists
import com.revenexx.enums.OrderListKindTone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orderlists = Orderlists(client)

val result = orderlists.orderlistsKindsUpdate(
    id = "", 
    description = "Chemicals ordered against a standing lab protocol.", // (optional)
    descriptions = mapOf(
        "de" to "Chemikalien, die nach einem festen Laborprotokoll bestellt werden.",
        "en" to "Chemicals ordered against a standing lab protocol."
    ), // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Reagenzienliste",
        "en" to "Reagent list"
    ), // (optional)
    position = 2, // (optional)
    title = "Reagent list", // (optional)
    tone = OrderListKindTone.NEUTRAL, // (optional)
)
```
