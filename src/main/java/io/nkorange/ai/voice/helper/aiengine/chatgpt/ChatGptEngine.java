package io.nkorange.ai.voice.helper.aiengine.chatgpt;

import com.alibaba.fastjson.JSON;
import io.nkorange.ai.voice.helper.aiengine.AiEngine;
import io.nkorange.ai.voice.helper.tools.httpclient.RestResponse;
import io.nkorange.ai.voice.helper.tools.httpclient.RestResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ChatGptEngine implements AiEngine {

    private static final String API_BASE = "https://chat.openai.com/";

    private static final String CHAT_URL = "backend-api/conversation";

    private String conversationId;

    private String parentId;

    private String accessToken;

    private CloseableHttpClient httpClient;

    private HttpClientContext httpClientContext;

    @PostConstruct
    public void init() {
        httpClientContext = HttpClientContext.create();
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder
                // Define timeout
                .setConnectionTimeToLive(30L, TimeUnit.SECONDS);
        httpClient = clientBuilder.build();
    }

    @Override
    public String Ask(String question) throws Exception {

        String url = API_BASE + CHAT_URL;
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Host", "chat.openai.com"));
        headers.add(new BasicHeader("Accept", "text/event-stream"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        headers.add(new BasicHeader("Authorization", "Bearer " + accessToken));
        headers.add(new BasicHeader("X-Openai-Assistant-App-Id", ""));
        headers.add(new BasicHeader("Connection", "close"));
        headers.add(new BasicHeader("Referer", "https://chat.openai.com/chat"));
        headers.add(new BasicHeader("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8"));

        HttpPost post = new HttpPost(url);

        headers.forEach(post::addHeader);

        Question q = new Question();
        q.setAction("next");
        List<Question.Message> messages = new ArrayList<>();
        Question.Message message = new Question.Message();
        Question.Content content = new Question.Content();
        content.setContentType("text");
        content.setParts(new String[]{question});
        message.setContent(content);
        message.setId(UUID.randomUUID().toString());
        message.setRole("user");
        messages.add(message);
        q.setMessages(messages);
        q.setModel("text-davinci-002-render");
        q.setParentMessageId(parentId);
        q.setConversationId(conversationId);

        final String jsonPayloadStr = JSON.toJSONString(q);

        post.setEntity(new StringEntity(jsonPayloadStr));

        RestResponse restResponse = httpClient.execute(post, new RestResponseHandler(), httpClientContext);
        return restResponse.getResponseStr();
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
