```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Avatars;
import com.revenexx.enums.Theme;
import com.revenexx.enums.Timezone;
import com.revenexx.enums.Permissions;
import com.revenexx.enums.Output;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Avatars avatars = new Avatars(client);

avatars.avatarsGetScreenshot(
    "https://example.com", // url 
    Map.of("a", "b"), // headers (optional)
    1, // viewportWidth (optional)
    1, // viewportHeight (optional)
    1, // scale (optional)
    Theme.LIGHT, // theme (optional)
    "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15", // userAgent (optional)
    true, // fullpage (optional)
    "en-US", // locale (optional)
    Timezone.AFRICA_ABIDJAN, // timezone (optional)
    9.99, // latitude (optional)
    9.99, // longitude (optional)
    9.99, // accuracy (optional)
    true, // touch (optional)
    Permissions.GEOLOCATION, // permissions (optional)
    1, // sleep (optional)
    1, // width (optional)
    1, // height (optional)
    1, // quality (optional)
    Output.JPG, // output (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
