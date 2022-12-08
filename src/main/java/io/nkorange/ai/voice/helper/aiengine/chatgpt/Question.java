package io.nkorange.ai.voice.helper.aiengine.chatgpt;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class Question {

    private String action;

    private List<Message> messages;

    private String conversationId;

    private String parentMessageId;

    private String model;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(String parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @JSONType(naming = PropertyNamingStrategy.SnakeCase)
    public static class Message {

        private String id;

        private String role;

        private Content content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }
    }

    @JSONType(naming = PropertyNamingStrategy.SnakeCase)
    public static class Content {

        private String contentType;

        private String[] parts;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String[] getParts() {
            return parts;
        }

        public void setParts(String[] parts) {
            this.parts = parts;
        }
    }
}
