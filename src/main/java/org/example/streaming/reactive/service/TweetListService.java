package org.example.streaming.reactive.service;

import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.repository.TweetRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

//@Log4j
@Service
public class TweetListService {

    private final ApplicationEventPublisher publisher; // <1>
    private final TweetRepository tweetRepository; // <2>

    TweetListService(ApplicationEventPublisher publisher, TweetRepository tweetRepository){

        this.publisher = publisher;
        this.tweetRepository = tweetRepository;

    }

    public Flux<Tweets> all() { // <3>
        return this.tweetRepository.findAll();
    }
}
