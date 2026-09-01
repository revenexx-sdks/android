```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersOrganizations

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersOrganizations = CustomersOrganizations(client)

val result = customersOrganizations.customersAddressesUpdate(
    id = "", 
    city = "Berlin", // (optional)
    company = "Beispiel Industrietechnik GmbH", // (optional)
    contact_id = "", // (optional)
    country = "DE", // (optional)
    is_default = true, // (optional)
    name = "Anna Berger", // (optional)
    organization_id = "", // (optional)
    phone = "+49 30 5550123", // (optional)
    region = "Berlin", // (optional)
    street = "Musterstraße 12", // (optional)
    street2 = "Gebäude C, 2. OG", // (optional)
    type = "shipping", // (optional)
    zip = "10115", // (optional)
)
```
