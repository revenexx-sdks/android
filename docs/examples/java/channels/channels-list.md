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

channels.channelsList(
    "", // id (optional)
    "shop", // code (optional)
    "Shop", // name (optional)
    "{"en":"Shop","de":"Shop"}", // labels (optional)
    "storefront", // type (optional)
    ChannelStatus.ACTIVE, // status (optional)
    ChannelUnassignedVisibility.INHERIT, // unassigned_visibility (optional)
    true, // is_default (optional)
    1, // position (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
