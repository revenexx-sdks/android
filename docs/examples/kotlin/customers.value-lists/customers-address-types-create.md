```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersValueLists
import com.revenexx.enums.Tone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersValueLists = CustomersValueLists(client)

val result = customersValueLists.customersAddressTypesCreate(
    code = "", 
    title = "Shipping address", 
    description = "Where the goods go.", // (optional)
    descriptions = mapOf(
        "de" to "Wohin die Ware geliefert wird.",
        "en" to "Where the goods go."
    ), // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Lieferadresse",
        "en" to "Shipping address"
    ), // (optional)
    position = 1, // (optional)
    tone = tone.NEUTRAL, // (optional)
)
```
