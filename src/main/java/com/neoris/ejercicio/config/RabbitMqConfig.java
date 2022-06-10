package com.neoris.ejercicio.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${ejercicio.rabbitmq.queue}")
	String QUEUE_NAME;
	
	@Value("${ejercicio.rabbitmq.exchange}")
	String EXCHANGE_NAME;
	
	@Value("${ejercicio.rabbitmq.routingkey}")
    String ROUTING_KEY;
	
	//Creo una cola con un nombre
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME);
	}
	
	//Creo un exchange
	@Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
	
	//creo un binding
	@Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
	
	//cambiamos el comportamiento de rabbittemplate para que haga conversion de Json a clases Java
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
	    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	    return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
}
