package org.example.streaming.reactive.handler;

import org.example.streaming.reactive.model.Tweets;
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

    public Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.tweetListService.all());
    }



    private static Mono<ServerResponse> defaultReadResponse(Publisher<Tweets> tweets) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(tweets, Tweets.class);
    }
}
