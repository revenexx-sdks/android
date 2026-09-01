```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingValueLists
import com.revenexx.enums.Tone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingValueLists = ShippingValueLists(client)

val result = shippingValueLists.shippingWeightUnitsUpdate(
    id = "", 
    description = "When to pick this weight unit.", // (optional)
    descriptions = mapOf(
        "de" to "Wann diese Option zu wählen ist.",
        "en" to "When to pick this weight unit."
    ), // (optional)
    factor = 1000, // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Tonne",
        "en" to "Tonne"
    ), // (optional)
    position = 1, // (optional)
    title = "Tonne", // (optional)
    tone = tone.NEUTRAL, // (optional)
)
```
