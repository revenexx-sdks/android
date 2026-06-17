```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Pages;
import com.revenexx.enums.PageStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Pages pages = new Pages(client);

pages.pagesPagesUpdate(
    "", // id 
    "", // bundle (optional)
    Map.of("a", "b"), // meta (optional)
    "", // slug (optional)
    PageStatus.DRAFT, // status (optional)
    "", // title (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
