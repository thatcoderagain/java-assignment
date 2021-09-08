package com.beta.replyservice;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;


public class ReplyMessageTest {

    private static ReplyMessage replyMessage;

    @Test
    public void testDoubleReverse() {
        String payload = "11-hello";
        replyMessage = new ReplyMessage(payload);
        String result = replyMessage.getMessage();
        assertThat(result).isEqualTo("hello");
    }

    @Test
    public void testReverseMD5() {
        String payload = "12-hello";
        replyMessage = new ReplyMessage(payload);
        String result = replyMessage.getMessage();
        assertThat(result).isEqualTo("10e099145e3c91cd94ccb9bdc50d0495");
    }

    @Test
    public void testMd5Reverse() {
        String payload = "21-hello";
        replyMessage = new ReplyMessage(payload);
        String result = replyMessage.getMessage();
        assertThat(result).isEqualTo("295c710119d9179b67a2b4cba20414d5");
    }

    @Test
    public void testDoubleMD5() {
        String payload = "22-hello";
        replyMessage = new ReplyMessage(payload);
        String result = replyMessage.getMessage();
        assertThat(result).isEqualTo("69a329523ce1ec88bf63061863d9cb14");
    }
}