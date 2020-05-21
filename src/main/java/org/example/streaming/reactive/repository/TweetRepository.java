package org.example.streaming.reactive.repository;

import org.example.streaming.reactive.model.Tweets;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TweetRepository extends ReactiveCrudRepository<Tweets, String> {
    //Flux<Tweet> findAllByValue(String value);
}
