package br.com.helpdev.jynx.entrypoint.messaging;

import com.rabbitmq.client.Envelope;

import java.io.IOException;

@FunctionalInterface
public interface ConsumerListener {
    void handleDelivery(final Envelope envelope,
                        final byte[] body) throws IOException;
}
