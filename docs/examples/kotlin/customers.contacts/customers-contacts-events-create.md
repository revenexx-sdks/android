```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersContacts
import com.revenexx.enums.ContactActivityKind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersContacts = CustomersContacts(client)

val result = customersContacts.customersContactsEventsCreate(
    contact_id = "", 
    subject = "Called about the annual requirement", 
    actor = "vertrieb@example.com", // (optional)
    kind = ContactActivityKind.NOTE, // (optional)
    note = "Asked for a quote on the annual bolt requirement; call back in week 34.", // (optional)
    occurred_at = "2026-01-01T12:00:00Z", // (optional)
)
```
