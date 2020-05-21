package org.example.streaming.reactive.sse;

import org.example.streaming.reactive.model.Tweets;
import org.springframework.context.ApplicationEvent;

public class TweetEvent extends ApplicationEvent {

    public TweetEvent(Tweets source) {
        super(source);
    }
}
