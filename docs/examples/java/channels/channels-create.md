```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Channels;
import com.revenexx.enums.ChannelStatus;
import com.revenexx.enums.ChannelType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Channels channels = new Channels(client);

channels.channelsCreate(
    "", // code 
    "", // name 
    false, // is_default (optional)
    Map.of("a", "b"), // labels (optional)
    0, // position (optional)
    ChannelStatus.ACTIVE, // status (optional)
    ChannelType.STOREFRONT, // type (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
