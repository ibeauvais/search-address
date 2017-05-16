package fr.perso.searchaddress.service;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

import java.util.stream.Collectors;

public class SearchService {

    private final String hostname;
    private final int port;
    private final WebClient client;

    public SearchService(String hostname, int port, WebClient client) {
        this.hostname = hostname;
        this.port = port;
        this.client = client;
    }

    public void searchWord(String word, Handler<JsonArray> resultHandler) {
        client.get(port, hostname, "/address/address/_search")
                .sendJson(QueryBuilder.match_AllQuery(word),
                        httpResponseAsyncResult -> {
                            if (httpResponseAsyncResult.succeeded()) {
                                JsonObject jsonObject = httpResponseAsyncResult.result().bodyAsJsonObject();
                                JsonArray jsonArray = jsonObject.getJsonObject("hits").getJsonArray("hits")
                                        .stream()
                                        .map(j -> ((JsonObject) j).getJsonObject("_source"))
                                        .collect(Collectors.collectingAndThen(Collectors.toList(), JsonArray::new));
                                resultHandler.handle(jsonArray);
                            }
                        });
    }
}
