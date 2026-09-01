```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersContacts;
import com.revenexx.enums.CustomersContactsCreateRegistrationStatus;
import com.revenexx.enums.ContactStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersContacts customersContacts = new CustomersContacts(client);

customersContacts.customersContactsCreate(
    "einkauf@example.com", // email 
    "Anna", // first_name (optional)
    true, // is_primary (optional)
    "Einkaufsleitung", // job_title (optional)
    "Berger", // last_name (optional)
    "de-DE", // locale (optional)
    25000, // order_approval_limit (optional)
    "", // organization_id (optional)
    "+49 30 5550123", // phone (optional)
    CustomersContactsCreateRegistrationStatus.PENDING, // registration_status (optional)
    "buyer", // role (optional)
    ContactStatus.INVITED, // status (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
