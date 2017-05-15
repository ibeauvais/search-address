package fr.perso.searchaddress;

import fr.perso.searchaddress.api.AddressApi;
import io.vertx.core.Vertx;

public class App {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new AddressApi());

    }
}
