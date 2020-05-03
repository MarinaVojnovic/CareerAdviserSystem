package com.sbnz.career.adviser.dto;

public class MessageDto {

	String header;
	String message;
	
	public MessageDto() {
		
	}

	public MessageDto(String header, String message) {
		super();
		this.header = header;
		this.message = message;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
