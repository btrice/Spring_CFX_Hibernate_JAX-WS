package fr.msaidara.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "code", required = true)
	public String code;
	
	@XmlElement(name = "message", required = true)
	public String message;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message); 
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
