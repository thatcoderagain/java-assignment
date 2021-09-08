package com.beta.replyservice;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReplyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ReplyController controller;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    public void callReplyApi(String payload, String expectResult) throws Exception {
        String[] testUrls;

        assertThat(this.testRestTemplate
                .getForObject("http://localhost:" + port + "/reply/" + payload, String.class))
                .contains(expectResult);
    }

    @Test
    public void testDoubleReverse() {
        String payload = "11-hello";
        try {
            this.callReplyApi(payload, "hello");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testReverseMD5() {
        String payload = "12-hello";
        try {
            this.callReplyApi(payload, "10e099145e3c91cd94ccb9bdc50d0495");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testMd5Reverse() {
        String payload = "21-hello";
        try {
            this.callReplyApi(payload, "295c710119d9179b67a2b4cba20414d5");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testDoubleMD5() {
        String payload = "22-hello";
        try {
            this.callReplyApi(payload, "69a329523ce1ec88bf63061863d9cb14");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}