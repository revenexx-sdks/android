```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.messagingUpdateEmail(
    "", // messageId 
    List.of(), // attachments (optional)
    List.of(), // bcc (optional)
    List.of(), // cc (optional)
    "", // content (optional)
    false, // draft (optional)
    false, // html (optional)
    "", // scheduledAt (optional)
    "", // subject (optional)
    List.of(), // targets (optional)
    List.of(), // topics (optional)
    List.of(), // users (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
