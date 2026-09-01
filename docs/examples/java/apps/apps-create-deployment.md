```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.models.InputFile;
import com.revenexx.services.Apps;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Apps apps = new Apps(client);

apps.appsCreateDeployment(
    "", // functionId 
    true, // activate 
    InputFile.fromPath("file.png"), // code 
    "", // commands (optional)
    "", // entrypoint (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
