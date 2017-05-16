package fr.perso.searchaddress.service;

import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryBuilder {

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    public static Buffer match_AllQuery(String word) {
        String query = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"_all\": \"" + word + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        log.info(query);
        return Buffer.buffer(query);
    }
}
