```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.models.InputFile;
import com.revenexx.services.Storage;
import com.revenexx.enums.Visibility;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Storage storage = new Storage(client);

storage.assetStore(
    InputFile.fromPath("file.png"), // file 
    "", // alt_text (optional)
    "", // description (optional)
    "", // display_name (optional)
    "", // folder_id (optional)
    true, // keep_archive (optional)
    List.of(), // tags (optional)
    true, // unpack (optional)
    Visibility.PUBLIC, // visibility (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
