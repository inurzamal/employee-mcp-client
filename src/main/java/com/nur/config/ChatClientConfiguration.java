package com.nur.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {

    @Bean
    ChatClient employeeChatClient(
            ChatClient.Builder chatClientBuilder,
            ToolCallbackProvider toolCallbackProvider) {
        return chatClientBuilder
                .defaultTools(toolCallbackProvider)
                .build();
    }
}
