```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;
import com.revenexx.enums.ResourceType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.auditIndex(
    ResourceType.TEMPLATE, // resource_type (optional)
    "", // resource_id (optional)
    "", // subject (optional)
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
