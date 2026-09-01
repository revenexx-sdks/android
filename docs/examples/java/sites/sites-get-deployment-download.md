```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Sites;
import com.revenexx.enums.AppsGetDeploymentDownloadType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Sites sites = new Sites(client);

sites.sitesGetDeploymentDownload(
    "", // siteId 
    "", // deploymentId 
    AppsGetDeploymentDownloadType.SOURCE, // type (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
