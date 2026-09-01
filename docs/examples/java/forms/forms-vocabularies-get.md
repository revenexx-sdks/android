```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Forms;
import com.revenexx.enums.FormsVocabulariesGetName;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Forms forms = new Forms(client);

forms.formsVocabulariesGet(
    FormsVocabulariesGetName.FORM_STATUSES, // name 
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
