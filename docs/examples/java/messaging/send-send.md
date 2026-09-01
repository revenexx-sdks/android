```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.sendSend(
    "", // channel 
    "", // template 
    "", // to 
    List.of(), // attachments (optional)
    Map.of("a", "b"), // data (optional)
    true, // draft (optional)
    "", // locale (optional)
    "", // market (optional)
    "2026-01-01T12:00:00Z", // send_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
