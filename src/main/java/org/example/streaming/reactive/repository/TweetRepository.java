package org.example.streaming.reactive.repository;

import org.example.streaming.reactive.model.Tweet;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TweetRepository extends ReactiveCrudRepository<Tweet, String> {
    //Flux<Tweet> findAllByValue(String value);
}
