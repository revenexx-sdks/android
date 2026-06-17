```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Apps;
import com.revenexx.enums.Runtime;
import com.revenexx.enums.Scopes;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Apps apps = new Apps(client);

apps.appsUpdate(
    "", // functionId 
    "", // name 
    "", // commands (optional)
    false, // enabled (optional)
    "", // entrypoint (optional)
    List.of(), // events (optional)
    List.of(), // execute (optional)
    "", // installationId (optional)
    false, // logging (optional)
    "", // providerBranch (optional)
    "", // providerRepositoryId (optional)
    "", // providerRootDirectory (optional)
    false, // providerSilentMode (optional)
    Runtime.NODE_18_0, // runtime (optional)
    "", // schedule (optional)
    Scopes.SESSIONS_WRITE, // scopes (optional)
    "", // specification (optional)
    0, // timeout (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
