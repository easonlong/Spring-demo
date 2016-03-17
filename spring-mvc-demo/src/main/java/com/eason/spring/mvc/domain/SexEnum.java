package com.eason.spring.mvc.domain;

public enum SexEnum {
	M("M"), 
	F("F");
	private String value;

	private SexEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static boolean eligible(String value) {
		for (SexEnum sexEnum : values()) {
			if (sexEnum.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public static SexEnum fromValue(String value) throws Exception {
		for (SexEnum sexEnum : values()) {
			if (sexEnum.getValue().equals(value)) {
				return sexEnum;
			}
		}
		throw new Exception("The value[" + value + "] is not supported.");
	}
}
