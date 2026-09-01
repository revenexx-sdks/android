```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Forms;
import com.revenexx.enums.FormStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Forms forms = new Forms(client);

forms.formsList(
    "", // id (optional)
    "Contact request", // name (optional)
    "contact", // slug (optional)
    FormStatus.DRAFT, // status (optional)
    "2026-01-31T09:15:00Z", // created_at (optional)
    "2026-01-31T09:15:00Z", // updated_at (optional)
    50, // limit (optional)
    0, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
