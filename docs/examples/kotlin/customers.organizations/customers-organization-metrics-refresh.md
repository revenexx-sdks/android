```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersOrganizations

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersOrganizations = CustomersOrganizations(client)

val result = customersOrganizations.customersOrganizationMetricsRefresh(
    as_of = "2026-01-01T12:00:00Z", // (optional)
    cursor = "", // (optional)
    organization_ids = listOf(), // (optional)
)
```
