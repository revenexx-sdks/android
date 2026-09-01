```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersValueLists
import com.revenexx.enums.Tone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersValueLists = CustomersValueLists(client)

val result = customersValueLists.customersContactEventKindsUpdate(
    id = "", 
    description = "Somebody spoke to this person on the phone.", // (optional)
    descriptions = mapOf(
        "de" to "Es wurde mit dieser Person telefoniert.",
        "en" to "Somebody spoke to this person on the phone."
    ), // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Telefonat",
        "en" to "Phone call"
    ), // (optional)
    position = 1, // (optional)
    title = "Phone call", // (optional)
    tone = tone.NEUTRAL, // (optional)
)
```
