```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Forms;
import com.revenexx.enums.FormStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Forms forms = new Forms(client);

forms.formsUpdate(
    "", // id 
    List.of(Map.of(
        "$formkit", "text",
        "label", "Company",
        "name", "company",
        "validation", "required"
    ), Map.of(
        "$formkit", "email",
        "label", "Email",
        "name", "email",
        "validation", "required|email"
    ), Map.of(
        "$formkit", "textarea",
        "label", "What do you need a price for?",
        "name", "message",
        "rows", 4
    ), Map.of(
        "$el", "p",
        "children", "We answer price requests within one working day."
    )), // definition (optional)
    Map.of("a", "b"), // metadata (optional)
    "Price request", // name (optional)
    Map.of("a", "b"), // settings (optional)
    "price-request", // slug (optional)
    FormStatus.DRAFT, // status (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
