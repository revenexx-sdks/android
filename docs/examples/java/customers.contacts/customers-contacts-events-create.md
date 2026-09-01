```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CustomersContacts;
import com.revenexx.enums.ContactActivityKind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CustomersContacts customersContacts = new CustomersContacts(client);

customersContacts.customersContactsEventsCreate(
    "", // contact_id 
    "Called about the annual requirement", // subject 
    "vertrieb@example.com", // actor (optional)
    ContactActivityKind.NOTE, // kind (optional)
    "Asked for a quote on the annual bolt requirement; call back in week 34.", // note (optional)
    "2026-01-01T12:00:00Z", // occurred_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
