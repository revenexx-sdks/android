```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Search;
import com.revenexx.enums.Collection;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Search search = new Search(client);

search.searchSearchDocuments(
    Collection.GREETINGS, // collection 
    "", // facet_by (optional)
    "", // filter_by (optional)
    0, // page (optional)
    0, // per_page (optional)
    "", // q (optional)
    "", // query_by (optional)
    "", // sort_by (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
