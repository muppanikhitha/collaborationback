package com.niit.collaboration.model;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class BaseDomain {

	@Transient
	public String errCode;
	
	@Transient

	public String errMessage;
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
}
