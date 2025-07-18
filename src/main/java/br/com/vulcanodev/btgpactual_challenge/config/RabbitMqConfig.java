package br.com.vulcanodev.btgpactual_challenge.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public final static String ORDER_CREATED_QUEUE = "order-created";
}
