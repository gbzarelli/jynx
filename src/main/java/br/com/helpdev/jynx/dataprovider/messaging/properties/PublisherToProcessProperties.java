package br.com.helpdev.jynx.dataprovider.messaging.properties;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigProperties(prefix = "rabbitmq.publisher.to-process")
@Getter
@Setter
@ToString
public class PublisherToProcessProperties {

    private Exchange exchange;
    private String routingKey;

    @Getter
    @Setter
    @ToString
    public static class Exchange {
        private String name;
        private String type;
        private boolean durable = true;
    }

    public String getExchangeName() {
        return exchange.getName();
    }

}
