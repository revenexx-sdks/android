```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;
import com.revenexx.enums.Reason;
import com.revenexx.enums.Scope;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.suppressionStore(
    "", // address 
    "", // channel 
    Reason.HARD_BOUNCE, // reason 
    "2026-01-01T12:00:00Z", // expires_at (optional)
    "", // note (optional)
    Scope.ALL, // scope (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
