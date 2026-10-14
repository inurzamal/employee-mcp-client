package com.nur.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeMcpController {

    private final ChatClient chatClient;
    private final ToolCallbackProvider toolCallbackProvider;

    public EmployeeMcpController(
            ChatClient chatClient,
            ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient;
        this.toolCallbackProvider = toolCallbackProvider;
    }

    @GetMapping("/tools")
    public List<Map<String, String>> getTools() {

        return Arrays.stream(
                        toolCallbackProvider.getToolCallbacks())
                .map(callback -> Map.of(
                        "name", callback.getToolDefinition().name(),
                        "description",
                        callback.getToolDefinition().description()))
                .toList();
    }

    @PostMapping("/chat")
    public Map<String, String> chat(
            @RequestBody ChatRequest request) {

        String response = chatClient.prompt()
                .user(request.message())
                .call()
                .content();

        if (response == null || response.isBlank()) {
            response = "No employee information was found matching your request.";
        }

        return Map.of("response", response);
    }

    public record ChatRequest(String message) {
    }
}
