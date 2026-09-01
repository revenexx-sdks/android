```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Channels;
import com.revenexx.enums.ChannelStatus;
import com.revenexx.enums.ChannelUnassignedVisibility;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Channels channels = new Channels(client);

channels.channelsUpdate(
    "", // id 
    "shop", // code (optional)
    true, // is_default (optional)
    Map.of(
        "de", "Shop",
        "en", "Shop"
    ), // labels (optional)
    "Shop", // name (optional)
    1, // position (optional)
    ChannelStatus.ACTIVE, // status (optional)
    "storefront", // type (optional)
    ChannelUnassignedVisibility.INHERIT, // unassigned_visibility (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
