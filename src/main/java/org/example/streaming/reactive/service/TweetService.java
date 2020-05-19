package org.example.streaming.reactive.service;

import org.example.streaming.reactive.model.Tweet;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TweetService {

    @Autowired
    ReactiveMongoTemplate template;

    public Mono<Tweet> findById(String id) {
        return template.findById(id, Tweet.class);
    }

    public Flux<Tweet> findAll() {
        return template.findAll(Tweet.class);
    }

}
