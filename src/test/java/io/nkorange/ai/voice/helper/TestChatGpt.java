package io.nkorange.ai.voice.helper;


import io.nkorange.ai.voice.helper.aiengine.chatgpt.ChatGptEngine;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@EnableScheduling
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestChatGpt {

    @Autowired
    private ChatGptEngine engine;

    @Test
    public void testQa() throws Exception {
        engine.setAccessToken("input-your-token-here");
        engine.setParentId(UUID.randomUUID().toString());
        engine.setConversationId("");
        System.out.println(engine.Ask("What's your name?"));
    }
}
