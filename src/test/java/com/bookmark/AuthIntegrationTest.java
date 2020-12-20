package com.bookmark;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.*;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthIntegrationTest {

    private static final String CONFIG_PATH = "config.yml";

    @ClassRule
    public static final DropwizardAppRule<DropBookMarksConfiguration> RULE =
            new DropwizardAppRule<>(DropBookMarksApplication.class, CONFIG_PATH);

    private static final String TARGET = "http://localhost:8080";

    private static final String PATH = "/hello/secured";

    private static final HttpAuthenticationFeature FEATURE =
            HttpAuthenticationFeature.basic("username", "pass");

    //For testing https
    private static final String TARGET_HTTPS = "https://localhost:8443";
    private static final String KEY_STORE_FILE_NAME = "udemydropwizard.keystore";
    private static final String KEY_STORE_PASSWORD = "password";
    private Client sslClient;


    private Client client;


    @Before
    public void setUp() {
        //for testing https..
        SslConfigurator sslConfigurator = SslConfigurator.newInstance();
        sslConfigurator.trustStoreFile(KEY_STORE_FILE_NAME)
                .trustStorePassword(KEY_STORE_PASSWORD);
        SSLContext sslContext = sslConfigurator.createSSLContext();
        sslClient = ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .build();

        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() {
        client.close();
        sslClient.close();
    }

    @Test
    public void testSadPath() {
        Response response = client.target(TARGET).path(PATH).request().get();
        Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void happyPath() {
        String expected = "Hello secured dropwizard";
        client.register(FEATURE);
        String actual = client.target(TARGET).path(PATH).request(MediaType.TEXT_PLAIN).get(String.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void happyPathHTTPS() {
        String expected = "Hello secured dropwizard";
        sslClient.register(FEATURE);
        String actual = sslClient.target(TARGET_HTTPS).path(PATH).request(MediaType.TEXT_PLAIN).get(String.class);
        Assert.assertEquals(expected, actual);
    }


}
