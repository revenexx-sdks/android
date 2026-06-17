```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.messagingCreateMailgunProvider(
    "", // name 
    "", // providerId 
    "", // apiKey (optional)
    "", // domain (optional)
    false, // enabled (optional)
    "", // fromEmail (optional)
    "", // fromName (optional)
    false, // isEuRegion (optional)
    "", // replyToEmail (optional)
    "", // replyToName (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
