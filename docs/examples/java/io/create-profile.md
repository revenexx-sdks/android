```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Io;
import com.revenexx.enums.Direction;
import com.revenexx.enums.ApplyMode;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Io io = new Io(client);

io.createProfile(
    "", // app 
    Direction.IMPORT, // direction 
    "", // entity 
    "", // format 
    "", // name 
    "", // vendor 
    ApplyMode.UPSERT, // apply_mode (optional)
    Map.of("a", "b"), // mapping (optional)
    List.of(), // markets (optional)
    Map.of("a", "b"), // options (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
