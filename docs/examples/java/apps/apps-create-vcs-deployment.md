```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Apps;
import com.revenexx.enums.AppsCreateVcsDeploymentType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Apps apps = new Apps(client);

apps.appsCreateVcsDeployment(
    "", // functionId 
    "main", // reference 
    AppsCreateVcsDeploymentType.BRANCH, // type 
    true, // activate (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
