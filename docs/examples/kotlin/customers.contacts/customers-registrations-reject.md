```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersContacts

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersContacts = CustomersContacts(client)

val result = customersContacts.customersRegistrationsReject(
    contact_id = "", 
    reason = "Could not be verified as a commercial buyer.", 
    decided_by = "vertrieb@example.com", // (optional)
)
```
