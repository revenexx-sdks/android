```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Pages;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Pages pages = new Pages(client);

pages.pagesPagesCreate(
    "About us", // title 
    "standard", // bundle (optional)
    Map.of("a", "b"), // hostOptions (optional)
    Map.of("a", "b"), // meta (optional)
    "about-us", // slug (optional)
    "de", // sourceLanguage (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
