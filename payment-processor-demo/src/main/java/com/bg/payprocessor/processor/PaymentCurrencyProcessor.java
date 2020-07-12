package com.bg.payprocessor.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bg.payprocessor.generated.source.Payment;

@Component
public class PaymentCurrencyProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Payment payment = exchange.getIn().getBody(Payment.class);
		payment.setCurrency("PLN");
		exchange.getIn().setBody(payment);
		
	}
	
}
