package org.example.streaming.reactive.controller;

import org.example.streaming.reactive.handler.TweetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TweetEndPointConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(TweetHandler handler) { // <1>
        return
                route(i(GET("/tweets")), handler::all);
    }
    private static RequestPredicate i(RequestPredicate target) {
        return new CaseInsensitiveRequestPredicate(target);
    }
}
