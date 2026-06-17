```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;
import com.revenexx.enums.Priority;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.messagingCreatePush(
    "", // messageId 
    "", // action (optional)
    0, // badge (optional)
    "", // body (optional)
    "", // color (optional)
    false, // contentAvailable (optional)
    false, // critical (optional)
    Map.of("a", "b"), // data (optional)
    false, // draft (optional)
    "", // icon (optional)
    "", // image (optional)
    Priority.NORMAL, // priority (optional)
    "", // scheduledAt (optional)
    "", // sound (optional)
    "", // tag (optional)
    List.of(), // targets (optional)
    "", // title (optional)
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
