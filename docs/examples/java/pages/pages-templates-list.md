```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Pages;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Pages pages = new Pages(client);

pages.pagesTemplatesList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "Hero with two teasers", // label (optional)
    "Full-width hero followed by a two-column teaser row.", // description (optional)
    "standard", // page_bundle (optional)
    "content", // field_name (optional)
    true, // is_default (optional)
    "", // created_by (optional)
    "", // created_at (optional)
    "", // updated_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
