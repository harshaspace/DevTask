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
public class SendEmailAck {

	private String requestId;
	private String status;
	
	public String getRequestId() {
		return requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(requestId).append(status)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		SendEmailAck that = (SendEmailAck) obj;

		return new EqualsBuilder().append(this.requestId, that.requestId)
				.append(this.status, that.status).isEquals();
	}
	
	
	
	
}
