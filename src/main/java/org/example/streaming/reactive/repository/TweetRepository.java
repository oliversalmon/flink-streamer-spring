package org.example.streaming.reactive.repository;

import org.example.streaming.reactive.model.Tweets;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TweetRepository extends ReactiveCrudRepository<Tweets, String> {



}
