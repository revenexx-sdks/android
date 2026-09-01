```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Apps;
import com.revenexx.enums.Runtimes;
import com.revenexx.enums.UseCases;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Apps apps = new Apps(client);

apps.appsListTemplates(
    Runtimes.NODE_18_0, // runtimes (optional)
    UseCases.STARTER, // useCases (optional)
    1, // limit (optional)
    1, // offset (optional)
    true, // total (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
