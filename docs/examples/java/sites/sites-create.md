```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Sites;
import com.revenexx.enums.BuildRuntime;
import com.revenexx.enums.Framework;
import com.revenexx.enums.Adapter;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Sites sites = new Sites(client);

sites.sitesCreate(
    BuildRuntime.NODE_18_0, // buildRuntime 
    Framework.ANALOG, // framework 
    "", // name 
    "", // siteId 
    Adapter.STATIC, // adapter (optional)
    "npm run build", // buildCommand (optional)
    true, // enabled (optional)
    "index.html", // fallbackFile (optional)
    "npm install", // installCommand (optional)
    "", // installationId (optional)
    true, // logging (optional)
    "", // outputDirectory (optional)
    "main", // providerBranch (optional)
    "", // providerRepositoryId (optional)
    "", // providerRootDirectory (optional)
    true, // providerSilentMode (optional)
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
