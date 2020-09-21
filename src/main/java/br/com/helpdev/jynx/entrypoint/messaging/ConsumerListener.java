package br.com.helpdev.jynx.entrypoint.messaging;

import java.io.IOException;

@FunctionalInterface
public interface ConsumerListener {

    void handleDelivery(final byte[] payload) throws IOException;

}
