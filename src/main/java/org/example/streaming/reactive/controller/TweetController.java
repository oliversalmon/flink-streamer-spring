package org.example.streaming.reactive.controller;

import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.service.TweetListService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tweets", produces = MediaType.APPLICATION_JSON_VALUE)  // <2>
@org.springframework.context.annotation.Profile("classic")
public class TweetController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
    private final TweetListService tweetListRepository;

    TweetController(TweetListService tweetListService){
        this.tweetListRepository = tweetListService;
    }

    @GetMapping
    Publisher<Tweets> getAll() {
        return this.tweetListRepository.all();
    }


}
