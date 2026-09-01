```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Forms;
import com.revenexx.enums.FormSubmissionStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Forms forms = new Forms(client);

forms.formsSubmissionsCreate(
    Map.of(
        "company", "Example GmbH",
        "email", "buyer@example.com",
        "message", "Please quote 200 units of ACME-4711-BLK, delivered to Hamburg."
    ), // data 
    "", // form_id 
    "contact", // form_slug (optional)
    Map.of("a", "b"), // metadata (optional)
    "/contact", // source (optional)
    FormSubmissionStatus.NEW, // status (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
