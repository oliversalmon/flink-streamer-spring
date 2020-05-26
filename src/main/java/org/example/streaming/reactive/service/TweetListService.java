package org.example.streaming.reactive.service;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.FullDocument;
import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.repository.TweetRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import static java.util.Arrays.asList;

//@Log4j
@Service
public class TweetListService {

    private final ApplicationEventPublisher publisher; // <1>
    private final TweetRepository tweetRepository; // <2>
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    TweetListService(ApplicationEventPublisher publisher, TweetRepository tweetRepository, ReactiveMongoTemplate reactiveMongoTemplate){

        this.publisher = publisher;
        this.tweetRepository = tweetRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;

    }

    public Flux<Tweets> all() { // <3>
        return this.tweetRepository.findAll().limitRate(10);
    }


    public Mono<Tweets> findById(String Id){
        return this.tweetRepository.findById(Id);
    }


    public Flux<Tweets> streamTweets(){

        Flux changeStream = reactiveMongoTemplate
                .changeStream("TWITTER",
                        "tweets", ChangeStreamOptions.builder().filter(newAggregation(match(where("operationType").is("insert")))).fullDocumentLookup(FullDocument.UPDATE_LOOKUP).build(),Tweets.class);


         return changeStream;

    }


}
