package org.example.streaming.reactive.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.service.TweetListService;
import org.example.streaming.reactive.service.TweetService;
import org.example.streaming.reactive.sse.TweetsEvent;
import org.example.streaming.reactive.sse.TweetsPublisher;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ServerSentEventController {

    private TweetListService tweetListService;



    public ServerSentEventController(TweetListService tweetService) {
       this.tweetListService = tweetService;

    }

    @GetMapping(path = "/sse/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@CrossOrigin(origins = "http://localhost:3000")
    public  Flux<Tweets> tweets() {
        return this.tweetListService.streamTweets();
    }

    @GetMapping(path = "/sse/deletetweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@CrossOrigin(origins = "http://localhost:3000")
    public  Flux<Tweets> deleteTweets() {
        return this.tweetListService.streamDeletedTweets();
    }




}
