package com.bg.payprocessor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "PPD_VALID_MESSAGE")
public class ValidMessage implements Serializable {

	private static final long serialVersionUID = -6380516549468182024L;
	
	@Id
	@GenericGenerator(
	        name = "ValidMessageSequenceGenerator",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "PPD_VALID_MESSAGE_SEQ"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ValidMessageSequenceGenerator")
	@Column(name="NR_ID", nullable = false)
	private Long id;
	
	@Column(name="TX_MESSAGE_ID", nullable = false)
	private String messageId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
