```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PagesEditor;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PagesEditor pagesEditor = new PagesEditor(client);

pagesEditor.pagesEditorTemplatesCreate(
    "", // page_id 
    "Hero with two teasers", // label 
    List.of(), // uuids 
    "Full-width hero followed by a two-column teaser row.", // description (optional)
    "content", // fieldName (optional)
    true, // isDefault (optional)
    "standard", // pageBundle (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
