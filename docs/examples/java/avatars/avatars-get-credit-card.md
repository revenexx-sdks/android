```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Avatars;
import com.revenexx.enums.AvatarsGetCreditCardCode;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Avatars avatars = new Avatars(client);

avatars.avatarsGetCreditCard(
    AvatarsGetCreditCardCode.AMEX, // code 
    1, // width (optional)
    1, // height (optional)
    1, // quality (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
