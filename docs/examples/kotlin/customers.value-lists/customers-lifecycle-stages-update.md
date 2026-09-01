```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersValueLists
import com.revenexx.enums.Tone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersValueLists = CustomersValueLists(client)

val result = customersValueLists.customersLifecycleStagesUpdate(
    id = "", 
    description = "Has ordered at least once and is being served.", // (optional)
    descriptions = mapOf(
        "de" to "Hat mindestens einmal bestellt und wird betreut.",
        "en" to "Has ordered at least once and is being served."
    ), // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Kunde",
        "en" to "Customer"
    ), // (optional)
    position = 1, // (optional)
    title = "Customer", // (optional)
    tone = tone.NEUTRAL, // (optional)
)
```
