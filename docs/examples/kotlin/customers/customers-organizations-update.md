```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Customers
import com.revenexx.enums.OrganizationStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customers = Customers(client)

val result = customers.customersOrganizationsUpdate(
    id = "", 
    name = "", // (optional)
    settings = mapOf( "a" to "b" ), // (optional)
    status = OrganizationStatus.ACTIVE, // (optional)
    vat_id = "", // (optional)
)
```
