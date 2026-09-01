```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingValueLists;
import com.revenexx.enums.Tone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingValueLists shippingValueLists = new ShippingValueLists(client);

shippingValueLists.shippingServiceLevelsUpdate(
    "", // id 
    "When to pick this service level.", // description (optional)
    Map.of(
        "de", "Wann diese Option zu wählen ist.",
        "en", "When to pick this service level."
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Night courier",
        "en", "Night courier"
    ), // labels (optional)
    1, // position (optional)
    "Night courier", // title (optional)
    Tone.NEUTRAL, // tone (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
