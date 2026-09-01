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
    "npm install", // commands (optional)
    true, // enabled (optional)
    "src/main.js", // entrypoint (optional)
    List.of(), // events (optional)
    List.of("any"), // execute (optional)
    "", // installationId (optional)
    true, // logging (optional)
    "main", // providerBranch (optional)
    "", // providerRepositoryId (optional)
    "", // providerRootDirectory (optional)
    true, // providerSilentMode (optional)
    Runtime.NODE_18_0, // runtime (optional)
    "0 3 * * *", // schedule (optional)
    Scopes.SESSIONS_WRITE, // scopes (optional)
    "s-1vcpu-512mb", // specification (optional)
    1, // timeout (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
