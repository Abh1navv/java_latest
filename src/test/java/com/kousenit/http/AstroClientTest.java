package com.kousenit.http;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

class AstroClientTest {
    private final AstroClient client = new AstroClient();

    @Test
    void checkJsonOutput() throws IOException {
        Assumptions.assumeTrue(
                InetAddress.getByName("api.open-notify.org").isReachable(2000),
                "api.open-notify.org is down");

        System.out.println(client.getJsonResponse());
    }

    @Test
    void testDeserializeToRecords() {
        AstroResponse response = client.getAstroResponse();
        System.out.println(response);
        assertAll(
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
    }
}