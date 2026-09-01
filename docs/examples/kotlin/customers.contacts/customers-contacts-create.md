```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersContacts
import com.revenexx.enums.CustomersContactsCreateRegistrationStatus
import com.revenexx.enums.ContactStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersContacts = CustomersContacts(client)

val result = customersContacts.customersContactsCreate(
    email = "einkauf@example.com", 
    first_name = "Anna", // (optional)
    is_primary = true, // (optional)
    job_title = "Einkaufsleitung", // (optional)
    last_name = "Berger", // (optional)
    locale = "de-DE", // (optional)
    order_approval_limit = 25000, // (optional)
    organization_id = "", // (optional)
    phone = "+49 30 5550123", // (optional)
    registration_status = Customers.contacts.createRegistration_status.PENDING, // (optional)
    role = "buyer", // (optional)
    status = ContactStatus.INVITED, // (optional)
)
```
