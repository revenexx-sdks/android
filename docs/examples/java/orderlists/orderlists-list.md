```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orderlists;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orderlists orderlists = new Orderlists(client);

orderlists.orderlistsList(
    "", // owner_id (optional)
    "", // organization_id (optional)
    "shopping", // kind (optional)
    50, // limit (optional)
    0, // offset (optional)
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
