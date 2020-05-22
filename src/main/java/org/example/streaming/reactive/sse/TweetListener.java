package org.example.streaming.reactive.sse;



import com.mongodb.async.client.ChangeStreamIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.conversions.Bson;
import org.example.streaming.reactive.model.Tweets;
import org.example.streaming.reactive.repository.TweetRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class TweetListener implements ApplicationListener<ApplicationReadyEvent> {


    TweetRepository repository;
    ReactiveMongoTemplate reactiveMongoTemplate;

    TweetListener(TweetRepository repository, ReactiveMongoTemplate reactiveMongoTemplate){
        this.repository = repository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
    //start listening to mongo and publish
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {



        MongoCollection<Document> collection = reactiveMongoTemplate.getCollection("myTargetCollection");

// Create $match pipeline stage.
        List<Bson> pipeline = singletonList(Aggregates.match(
                Filters.in("operationType", "insert")));

// Create the change stream cursor, passing the pipeline to the
// collection.watch() method

        //MongoCursor<Document> cursor = collection.watch(pipeline);


    }
}
