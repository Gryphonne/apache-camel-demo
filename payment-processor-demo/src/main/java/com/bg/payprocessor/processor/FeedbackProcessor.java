package com.bg.payprocessor.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bg.payprocessor.generated.feedback.FeedbackMessage;

@Component
public class FeedbackProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		FeedbackMessage feedbackMessage = new FeedbackMessage();
		
		feedbackMessage.setBreadcrumbid(exchange.getIn().getHeader("breadcrumbId").toString());
		feedbackMessage.setPaymentstatus(exchange.getIn().getHeader("Payment Status").toString());
		
		exchange.getIn().setBody(feedbackMessage);
		
	}
	
}
