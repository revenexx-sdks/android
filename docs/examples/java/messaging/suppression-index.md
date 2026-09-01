```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;
import com.revenexx.enums.Scope;
import com.revenexx.enums.Reason;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.suppressionIndex(
    "", // channel (optional)
    Scope.ALL, // scope (optional)
    Reason.HARD_BOUNCE, // reason (optional)
    "", // address (optional)
    1, // limit (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
