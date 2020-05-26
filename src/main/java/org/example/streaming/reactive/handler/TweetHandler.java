package org.example.streaming.reactive.handler;

import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.service.TweetListService;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        return defaultStreamReadResponse(this.tweetListService.all());
    }
    public Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.tweetListService.findById(id(r)));
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    public Mono<ServerResponse> getTweetStream(ServerRequest r) {
        return defaultStreamReadResponseChangeEvent(this.tweetListService.streamTweets());
    }



    private static Mono<ServerResponse> defaultReadResponse(Publisher<Tweets> tweets) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(tweets, Tweets.class);
    }

    private static Mono<ServerResponse> defaultStreamReadResponse(Publisher<Tweets> tweets) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(tweets, Tweets.class);
    }

    private static Mono<ServerResponse> defaultStreamReadResponseChangeEvent(Publisher<Tweets> tweets) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(tweets, Tweets.class);
    }

    private static String id(ServerRequest r) {
        return r.pathVariable("id");
    }
}
