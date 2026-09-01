```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PagesCollaboration;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PagesCollaboration pagesCollaboration = new PagesCollaboration(client);

pagesCollaboration.pagesEditorCommentsUpdate(
    "", // page_id 
    "", // uuid 
    "<p>Please shorten this headline.</p>", // body 
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
