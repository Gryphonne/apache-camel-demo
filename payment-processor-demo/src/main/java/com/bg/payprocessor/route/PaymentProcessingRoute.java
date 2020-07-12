package com.bg.payprocessor.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bg.payprocessor.config.JAXBContextConfig;
import com.bg.payprocessor.processor.PaymentCurrencyProcessor;
import com.bg.payprocessor.service.PersistenceService;

@Component
public class PaymentProcessingRoute extends RouteBuilder {
	
	@Autowired
	PaymentCurrencyProcessor paymentCurrencyProcessor;
	
	@Autowired
	JAXBContextConfig jaxbContextConfig;
	
	@Autowired
	PersistenceService persistenceService;
	
	@Override
	public void configure() throws Exception {
		
		from("activemq:inputItemQueue")
			.errorHandler(deadLetterChannel("activemq:errorItemQueue"))
				.log("Received message: " + "${body}")
					.to(("validator:file:{{payment.schema}}")).log("Message succesfully validated")
					.setHeader("Payment Status", constant("RCVD")).log("Header enriched: [$simple{in.header.Payment Status}]")
						.wireTap("direct:feedbackRoute")
						.to("direct:continuePaymentValidation");					
					
		from("direct:continuePaymentValidation")
			.choice()
				.when(xpath("/*[local-name()='payment']/currency = 'EUR'")).log("Message currency is valid. Succesfull validation")
					.to("direct:continueProcessing")
				.otherwise().log("Message currency = []. Invalid currency detected. Message will be discarded with status RJCT")
					.setHeader("Payment Status", constant("RJCT")).log("Header enriched: [$simple{in.header.Payment Status}]")
					.wireTap("direct:feedbackRoute")
					.to("activemq:errorItemQueue")
				.end();

		from("direct:continueProcessing")
			.setHeader("Payment Status", constant("ACCP")).log("Header enriched: [$simple{in.header.Payment Status}]")
			.wireTap("direct:feedbackRoute")
				.unmarshal(jaxbContextConfig.jaxbPaymentContext())
					.process(paymentCurrencyProcessor)
				.marshal(jaxbContextConfig.jaxbPaymentContext())
					.setHeader("Payment Status", constant("ACSC")).log("Header enriched: [$simple{in.header.Payment Status}]. Finished processing message.")
					.wireTap("direct:feedbackRoute")
					.bean(persistenceService, "persistMessageHeaderId")
						.to("activemq:outputItemQueue");
		
	}

}