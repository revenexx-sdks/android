```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PagesEditor;
import com.revenexx.enums.PageEditStateStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PagesEditor pagesEditor = new PagesEditor(client);

pagesEditor.pagesEditorEditStates(
    PageEditStateStatus.ACTIVE, // status (optional)
    1, // limit (optional)
    1, // offset (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
