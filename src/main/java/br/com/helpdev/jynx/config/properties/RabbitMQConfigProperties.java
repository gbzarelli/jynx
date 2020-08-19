package br.com.helpdev.jynx.config.properties;

import com.rabbitmq.client.ConnectionFactory;
import io.quarkus.arc.config.ConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigProperties(prefix = "rabbitmq")
@Getter
@Setter
@ToString
public class RabbitMQConfigProperties {

    private String host;
    private Integer port = ConnectionFactory.DEFAULT_AMQP_PORT;
    private String vhost;
    private String username;
    private String password;

}
