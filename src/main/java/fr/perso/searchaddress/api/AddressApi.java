package fr.perso.searchaddress.api;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;

public class AddressApi extends AbstractVerticle {

    @Override
    public void start() {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        WebClient client = WebClient.create(vertx);

        router.get("/api/address/:searchText").handler(routingContext -> {
            String searchText = routingContext.request().getParam("searchText");

            client.get(9200,"localhost","/address/address/_search")
                    .sendJson(Buffer.buffer(""), httpResponseAsyncResult -> {

                    });

            routingContext.response().end(searchText);
        });

        server.requestHandler(router::accept)
                .listen(8080);
    }
}
