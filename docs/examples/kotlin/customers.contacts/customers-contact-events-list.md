```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersContacts

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersContacts = CustomersContacts(client)

val result = customersContacts.customersContactEventsList(
    id = "", // (optional)
    contact_id = "", // (optional)
    organization_id = "", // (optional)
    kind = "call", // (optional)
    name = "activity.call", // (optional)
    subject = "Called about the annual requirement", // (optional)
    actor = "vertrieb@example.com", // (optional)
    occurred_at = "2026-01-01T12:00:00Z", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
)
```
