```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersContacts;
import com.revenexx.enums.Status;
import com.revenexx.enums.RegistrationStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersContacts customersContacts = new CustomersContacts(client);

customersContacts.customersContactsList(
    "", // id (optional)
    "", // organization_id (optional)
    "einkauf@example.com", // email (optional)
    "Anna", // first_name (optional)
    "Berger", // last_name (optional)
    "+49 30 5550123", // phone (optional)
    "Einkaufsleitung", // job_title (optional)
    "buyer", // role (optional)
    Status.INVITED, // status (optional)
    9.99, // order_approval_limit (optional)
    RegistrationStatus.PENDING, // registration_status (optional)
    "2026-01-01T12:00:00Z", // registration_decided_at (optional)
    "vertrieb@example.com", // registration_decided_by (optional)
    "Could not be verified as a commercial buyer.", // registration_reason (optional)
    "de-DE", // locale (optional)
    true, // is_primary (optional)
    "", // external_user_id (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
