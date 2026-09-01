```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersOrganizations
import com.revenexx.enums.OrganizationStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersOrganizations = CustomersOrganizations(client)

val result = customersOrganizations.customersOrganizationsUpdate(
    id = "", 
    branche = "Maschinenbau", // (optional)
    credit_limit = 5000, // (optional)
    customer_number = "K-10042", // (optional)
    delivery_block = true, // (optional)
    lifecycle_stage = "customer", // (optional)
    name = "Beispiel Industrietechnik GmbH", // (optional)
    payment_terms = "net_30", // (optional)
    price_list = "standard", // (optional)
    settings = mapOf(
        "account_manager" to "sales-north",
        "delivery_tour" to "tuesday",
        "self_pickup" to true
    ), // (optional)
    status = OrganizationStatus.ACTIVE, // (optional)
    vat_id = "DE123456789", // (optional)
)
```
