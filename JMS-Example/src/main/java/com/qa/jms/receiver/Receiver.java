package com.qa.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@JmsListener(destination = "${spring.jms.template.default-destination}")
	public void receiveMessage(String msg) {
		LOGGER.info("Received <" + msg + ">");
	}
}