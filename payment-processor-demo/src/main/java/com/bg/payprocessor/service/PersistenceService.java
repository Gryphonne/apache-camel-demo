package com.bg.payprocessor.service;

import org.apache.camel.Exchange;

public interface PersistenceService {

	void persistMessageHeaderId(Exchange exchange);

}
