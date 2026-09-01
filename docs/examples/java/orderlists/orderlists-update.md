```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orderlists;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orderlists orderlists = new Orderlists(client);

orderlists.orderlistsUpdate(
    "", // id 
    "shopping", // kind (optional)
    Map.of(
        "department", "facility",
        "erp_reference", "REQ-2026-0042"
    ), // metadata (optional)
    "Weekly office supplies", // name (optional)
    true, // shared (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
