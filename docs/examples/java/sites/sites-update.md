```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Sites;
import com.revenexx.enums.Framework;
import com.revenexx.enums.Adapter;
import com.revenexx.enums.BuildRuntime;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Sites sites = new Sites(client);

sites.sitesUpdate(
    "", // siteId 
    Framework.ANALOG, // framework 
    "", // name 
    Adapter.STATIC, // adapter (optional)
    "", // buildCommand (optional)
    BuildRuntime.NODE_18_0, // buildRuntime (optional)
    false, // enabled (optional)
    "", // fallbackFile (optional)
    "", // installCommand (optional)
    "", // installationId (optional)
    false, // logging (optional)
    "", // outputDirectory (optional)
    "", // providerBranch (optional)
    "", // providerRepositoryId (optional)
    "", // providerRootDirectory (optional)
    false, // providerSilentMode (optional)
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
