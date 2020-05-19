package org.example.streaming.reactive.config;


import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class ReactiveMongoConfig {

    @Autowired
    MongoClient mongoClient;


    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {

        return new ReactiveMongoTemplate(mongoClient, "TWITTER");
    }
}

