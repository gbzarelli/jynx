package br.com.helpdev.jynx.config.impl;

import br.com.helpdev.jynx.config.RabbitMQConfigProperties;
import br.com.helpdev.jynx.config.RabbitMQConnector;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
class RabbitMQConnectorImpl implements RabbitMQConnector {

    private final ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    @Inject
    RabbitMQConnectorImpl(final RabbitMQConfigProperties config) {
        factory = new ConnectionFactory();
        factory.setHost(config.getHost());
        factory.setPort(config.getPort());
        factory.setVirtualHost(config.getVhost());
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        factory.setAutomaticRecoveryEnabled(true);
    }

    void onStart(@Observes final StartupEvent ev) throws IOException, TimeoutException {
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    void onStop(@Observes final ShutdownEvent ev) {
        try {
            channel.close();
            connection.close();
        } catch (final Exception ignored) {
        }
    }

    public Channel getChannel() {
        return channel;
    }
}