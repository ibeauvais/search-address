package fr.perso.searchaddress;


import fr.perso.searchaddress.service.SearchService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;

public class App extends AbstractVerticle {

    @Override
    public void start() {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        WebClient client = WebClient.create(vertx);

        SearchService searchService = new SearchService("localhost", 9200, client);

        router.get("/api/address/search/:searchText")
                .handler(routingContext -> {
                    String searchText = routingContext.request().getParam("searchText");
                    searchService.searchWord(searchText, result ->
                            routingContext.response()
                                    .putHeader("content-type", "application/json; charset=utf-8")
                                    .end(Json.encode(result))
                    );
                });

        server.requestHandler(router::accept)
                .listen(8080);
    }

}
