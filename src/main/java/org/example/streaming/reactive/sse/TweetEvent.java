package org.example.streaming.reactive.sse;

import org.example.streaming.reactive.model.Tweet;
import org.springframework.context.ApplicationEvent;

public class TweetEvent extends ApplicationEvent {

    public TweetEvent(Tweet source) {
        super(source);
    }
}
