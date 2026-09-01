```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Pages;
import com.revenexx.enums.PageStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Pages pages = new Pages(client);

pages.pagesPagesList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "standard", // bundle (optional)
    PageStatus.DRAFT, // status (optional)
    "contact", // q (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
