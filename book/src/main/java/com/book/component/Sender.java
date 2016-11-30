package com.book.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
// @EnableBinding(BookingSource.class)
public class Sender {
	private static final Logger logger = LoggerFactory.getLogger(Sender.class);

	public Sender() {

	}

	RabbitMessagingTemplate template;

	@Autowired
	Sender(RabbitMessagingTemplate template) {
		this.template = template;
	}

	@Bean
	Queue queue() {
		return new Queue("InventoryQ", false);
	}

	@Bean
	Queue queue1() {
		return new Queue("CheckInQ", false);
	}

	// @Output(BookingSource.InventoryQ)
	// @Autowired
	// private MessageChannel messageChannel;

	public void send(Object message) {
		template.convertAndSend("InventoryQ", message);
		// boolean result = messageChannel.send(MessageBuilder
		// .withPayload(message).build());
		logger.info("booking event successfully delivered: ");
	}
}

// interface BookingSource {
// public static String InventoryQ = "inventoryQ";
//
// @Output("inventoryQ")
// public MessageChannel inventoryQ();
//
// }
