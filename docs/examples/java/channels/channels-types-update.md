```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Channels;
import com.revenexx.enums.ChannelTypeTone;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Channels channels = new Channels(client);

channels.channelsTypesUpdate(
    "", // id 
    "A web shop a human browses.", // description (optional)
    Map.of(
        "de", "Shop",
        "en", "Shop"
    ), // descriptions (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Shop",
        "en", "Shop"
    ), // labels (optional)
    1, // position (optional)
    "Product feed", // title (optional)
    ChannelTypeTone.NEUTRAL, // tone (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
