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
public class TweetsPublisher implements
    ApplicationListener<TweetsEvent>, // <1>
    Consumer<FluxSink<TweetsEvent>> { //<2>

    private final Executor executor;
    private final BlockingQueue<TweetsEvent> queue =
        new LinkedBlockingQueue<>(); // <3>

    TweetsPublisher(Executor executor) {
        this.executor = executor;
    }

    // <4>
    @Override
    public void onApplicationEvent(TweetsEvent event) {
        this.queue.offer(event);
    }

     @Override
    public void accept(FluxSink<TweetsEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    TweetsEvent event = queue.take(); // <5>
                    sink.next(event); // <6>
                }
                catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}
