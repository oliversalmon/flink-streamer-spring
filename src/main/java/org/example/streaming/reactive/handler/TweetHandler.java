package org.example.streaming.reactive.handler;

import org.example.streaming.reactive.model.Tweet;
import org.example.streaming.reactive.service.TweetListService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TweetHandler {

    private final TweetListService tweetListService;

    TweetHandler(TweetListService tweetListService){
        this.tweetListService = tweetListService;
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.tweetListService.all());
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<Tweet> profiles) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(profiles, Tweet.class);
    }
}
