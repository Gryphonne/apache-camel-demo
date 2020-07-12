package com.bg.payprocessor.service;


import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bg.payprocessor.domain.ValidMessage;
import com.bg.payprocessor.repository.PersistenceRepository;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Autowired
	PersistenceRepository persistenceRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void persistMessageHeaderId(Exchange exchange) {

		ValidMessage validMessage = new ValidMessage();
		validMessage.setMessageId(exchange.getIn().getHeader("breadcrumbId").toString());
		persistenceRepository.saveAndFlush(validMessage);
		
	}
	
}