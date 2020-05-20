package org.example.streaming.reactive.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.streaming.reactive.sse.TweetEvent;
import org.example.streaming.reactive.sse.TweetPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerSentEventController {
    private final Flux<TweetEvent> events;
    private final ObjectMapper objectMapper;

    public ServerSentEventController(TweetPublisher eventPublisher, ObjectMapper objectMapper) {
        this.events = Flux.create(eventPublisher).share();
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/sse/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public Flux<String> tweets() {
        return this.events.map(pce -> {
            try {
                return objectMapper.writeValueAsString(pce) + "\n\n";
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
