package br.com.helpdev.jynx.config;

import com.rabbitmq.client.Channel;

public interface RabbitMQConnector {
    Channel getChannel();
}
