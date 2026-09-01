```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Messaging;
import com.revenexx.enums.MessageClass;
import com.revenexx.enums.WhatsappCategory;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Messaging messaging = new Messaging(client);

messaging.templateUpdatePatch(
    "", // id 
    "", // body_html (optional)
    "", // body_text (optional)
    "", // content_sid (optional)
    List.of(), // design (optional)
    true, // enabled (optional)
    "", // layout_id (optional)
    List.of(), // markets (optional)
    MessageClass.TRANSACTIONAL, // message_class (optional)
    "", // subject (optional)
    true, // test_mode (optional)
    "", // title (optional)
    "2026-01-01T12:00:00Z", // valid_from (optional)
    "2026-01-01T12:00:00Z", // valid_until (optional)
    List.of(), // variable_defaults (optional)
    List.of(), // variables (optional)
    WhatsappCategory.MARKETING, // whatsapp_category (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
