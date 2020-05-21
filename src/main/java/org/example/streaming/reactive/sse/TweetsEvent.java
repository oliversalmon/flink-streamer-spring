package org.example.streaming.reactive.sse;

import org.example.streaming.reactive.model.Tweets;
import org.springframework.context.ApplicationEvent;

public class TweetsEvent extends ApplicationEvent {

    public TweetsEvent(Tweets source) {
        super(source);
    }
}
