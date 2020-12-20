package com.bookmark.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class HelloResourceTest {

    //This is for in-memory jersey server..
    @ClassRule
    public static final ResourceTestRule RULE =
            ResourceTestRule.builder().addResource(new HelloResource()).build();

    @Test
    public void getMessageTest() {
        String expected = "Hello dropwizard";
        //In-memory jersey server..
        String actual = RULE.getJerseyTest()
                .target("/hello")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        assertEquals(expected, actual);
    }
}
