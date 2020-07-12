package com.bg.payprocessor.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bg.payprocessor.config.JAXBContextConfig;
import com.bg.payprocessor.processor.FeedbackProcessor;

@Component
public class FeedbackRoute extends RouteBuilder {

	@Autowired
	JAXBContextConfig jaxbContextConfig;
	
	@Autowired
	FeedbackProcessor feedbackProcessor;
	
	@Override
	public void configure() throws Exception {

		from("direct:feedbackRoute")
			.process(feedbackProcessor)
				.log("Feedback Message created with status: [$simple{in.body.paymentstatus}]")
					.marshal(jaxbContextConfig.jaxbFeedbackContext())
						.to("activemq:feedbackItemQueue");
	}	

}
