/**
 * 
 */
package com.mailsender.core.message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author harsha
 *
 */
public class SendEmailRequest {

	private String requestId;
	private String senderName;
	private String recipientEmail;
	private String subject;
	private String messageText;

	public String getRequestId() {
		return requestId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(requestId).append(senderName)
				.append(recipientEmail).append(subject).append(messageText)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		SendEmailRequest that = (SendEmailRequest) obj;

		return new EqualsBuilder().append(this.requestId, that.requestId)
				.append(this.senderName, that.senderName)
				.append(this.recipientEmail, that.recipientEmail)
				.append(this.subject, that.subject)
				.append(this.messageText, that.messageText).isEquals();
	}

}
