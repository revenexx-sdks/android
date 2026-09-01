```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Io;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Io io = new Io(client);

io.listIoEntities(new CoroutineCallback<>((result, error) -> {
    if (error != null) {
        error.printStackTrace();
        return;
    }

    Log.d("Revenexx", result.toString());
}));
```
