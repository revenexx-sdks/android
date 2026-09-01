```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersContacts;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersContacts customersContacts = new CustomersContacts(client);

customersContacts.customersContactEventsList(
    "", // id (optional)
    "", // contact_id (optional)
    "", // organization_id (optional)
    "call", // kind (optional)
    "activity.call", // name (optional)
    "Called about the annual requirement", // subject (optional)
    "vertrieb@example.com", // actor (optional)
    "2026-01-01T12:00:00Z", // occurred_at (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
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
