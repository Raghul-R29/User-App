package com.jsp.user_app.util;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ResponseStructure<T> {
	private T data;
	private LocalDateTime timeStamp;
	private int statusCode;
	private String message;

}
