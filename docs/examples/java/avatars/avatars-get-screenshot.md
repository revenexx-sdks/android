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
    "", // url 
    Map.of("a", "b"), // headers (optional)
    0, // viewportWidth (optional)
    0, // viewportHeight (optional)
    0, // scale (optional)
    Theme.LIGHT, // theme (optional)
    "", // userAgent (optional)
    false, // fullpage (optional)
    "", // locale (optional)
    Timezone.AFRICA_ABIDJAN, // timezone (optional)
    0, // latitude (optional)
    0, // longitude (optional)
    0, // accuracy (optional)
    false, // touch (optional)
    Permissions.GEOLOCATION, // permissions (optional)
    0, // sleep (optional)
    0, // width (optional)
    0, // height (optional)
    0, // quality (optional)
    Output.JPG, // output (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
