```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PaymentsLedger;
import com.revenexx.enums.PaymentsVocabulariesGetName;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PaymentsLedger paymentsLedger = new PaymentsLedger(client);

paymentsLedger.paymentsVocabulariesGet(
    PaymentsVocabulariesGetName.DUNNING_STAGES, // name 
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
