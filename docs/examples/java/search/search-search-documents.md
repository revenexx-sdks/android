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
    Collection.PRODUCTS, // collection 
    "", // exclude_fields (optional)
    "", // facet_by (optional)
    "", // filter_by (optional)
    "", // group_by (optional)
    "", // highlight_full_fields (optional)
    "", // include_fields (optional)
    1, // max_facet_values (optional)
    1, // num_typos (optional)
    1, // page (optional)
    1, // per_page (optional)
    "", // prefix (optional)
    "", // q (optional)
    "", // query_by (optional)
    "", // sort_by (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
