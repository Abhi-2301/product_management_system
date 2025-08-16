package com.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T>{

	private int statusCode;
	private LocalDateTime localdatetime;
	private String message;
	private T data;
}
