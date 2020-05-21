package org.example.streaming.reactive.service;

import org.example.streaming.reactive.model.Tweets;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TweetService {

    @Autowired
    ReactiveMongoTemplate template;

    public Mono<Tweets> findById(String id) {
        return template.findById(id, Tweets.class);
    }

    public Flux<Tweets> findAll() {
        return template.findAll(Tweets.class);
    }

}
