```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Carts;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Carts carts = new Carts(client);

carts.cartsCreate(
    "", // channel_id (optional)
    "", // contact_id (optional)
    "EUR", // currency (optional)
    true, // is_current (optional)
    Map.of(
        "campaign", "spring-catalogue",
        "locale", "de-DE",
        "source", "storefront"
    ), // metadata (optional)
    "Weekly order", // name (optional)
    "a1b2c3d4e5f6", // session_key (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
