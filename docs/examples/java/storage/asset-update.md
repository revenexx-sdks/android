```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Storage;
import com.revenexx.enums.Visibility;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Storage storage = new Storage(client);

storage.assetUpdate(
    "", // id 
    "", // alt_text (optional)
    "", // description (optional)
    "", // display_name (optional)
    "", // folder_id (optional)
    "", // name (optional)
    List.of(), // tags (optional)
    Visibility.PUBLIC, // visibility (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
