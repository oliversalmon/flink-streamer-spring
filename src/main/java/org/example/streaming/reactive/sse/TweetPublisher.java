package org.example.streaming.reactive.sse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
public class TweetPublisher implements
    ApplicationListener<TweetEvent>, // <1>
    Consumer<FluxSink<TweetEvent>> { //<2>

    private final Executor executor;
    private final BlockingQueue<TweetEvent> queue =
        new LinkedBlockingQueue<>(); // <3>

    TweetPublisher(Executor executor) {
        this.executor = executor;
    }

    // <4>
    @Override
    public void onApplicationEvent(TweetEvent event) {
        this.queue.offer(event);
    }

     @Override
    public void accept(FluxSink<TweetEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    TweetEvent event = queue.take(); // <5>
                    sink.next(event); // <6>
                }
                catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}
