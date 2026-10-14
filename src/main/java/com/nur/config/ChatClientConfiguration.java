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
                .defaultSystem("""
                        You are a helpful Employee Directory Assistant.
                        Use the available MCP tools to look up employees, departments, and details.
                        Always provide a clear, helpful, and natural language response based on the tool results.
                        If no employees or departments match the search, clearly state that no records were found.
                        """)
                .build();
    }
}
