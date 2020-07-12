package com.bg.payprocessor.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.context.annotation.Configuration;

import com.bg.payprocessor.generated.feedback.FeedbackMessage;
import com.bg.payprocessor.generated.source.Payment;

@Configuration
public class JAXBContextConfig {

	public JaxbDataFormat jaxbPaymentContext() throws JAXBException {
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Payment.class);
		xmlDataFormat.setContext(con);
		return xmlDataFormat;
	}
	
	public JaxbDataFormat jaxbFeedbackContext() throws JAXBException {
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(FeedbackMessage.class);
		xmlDataFormat.setContext(con);
		return xmlDataFormat;
	}
	
}
