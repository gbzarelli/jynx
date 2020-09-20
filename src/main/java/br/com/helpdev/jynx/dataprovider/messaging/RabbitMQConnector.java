package br.com.helpdev.jynx.dataprovider.messaging;

import com.rabbitmq.client.Channel;

interface RabbitMQConnector {
    Channel getChannel();
}
