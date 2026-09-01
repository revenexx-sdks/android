```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Io;
import com.revenexx.enums.Format;
import com.revenexx.enums.Mode;
import com.revenexx.enums.CreateImportTarget;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Io io = new Io(client);

io.createImport(
    "", // app 
    "", // entity 
    "", // object_key 
    "", // vendor 
    Format.CSV, // format (optional)
    List.of(), // keys (optional)
    1, // max_rejects (optional)
    Mode.UPSERT, // mode (optional)
    "", // profile_id (optional)
    CreateImportTarget.LIVE, // target (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
