```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PagesCollaboration;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PagesCollaboration pagesCollaboration = new PagesCollaboration(client);

pagesCollaboration.pagesEditorCommentsCreate(
    "", // page_id 
    "<p>Please shorten this headline.</p>", // body 
    List.of(), // blockUuids (optional)
    "", // parentUuid (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
