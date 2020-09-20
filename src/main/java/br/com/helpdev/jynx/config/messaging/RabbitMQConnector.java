package br.com.helpdev.jynx.config.messaging;

import com.rabbitmq.client.Channel;

public interface RabbitMQConnector {
    Channel getChannel();
}
