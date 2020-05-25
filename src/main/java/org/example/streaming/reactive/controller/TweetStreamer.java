package org.example.streaming.reactive.controller;

import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.service.TweetListService;
import org.example.streaming.reactive.service.TweetService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tweetsStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)  // <2>
@org.springframework.context.annotation.Profile("classic")
public class TweetStreamer {

    private final TweetListService tweetListRepository;

     TweetStreamer(TweetListService tweetListService){
        this.tweetListRepository = tweetListService;
    }

    @GetMapping
    Publisher<Tweets> getAll() {
        return this.tweetListRepository.all();
    }
}
