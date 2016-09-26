package com.hhfactory.entity.code;

public enum Status implements Encodable<String>{
	VALID("1", "valid"),
	INVALID("2", "invalid");
	
	private final String code;
	private final String name;
	private static final Decoder<String, Status> decoder = Decoder.create(values());

	private Status(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Status decode(String code) {
		return decoder.decode(code);
	}

	@Override
	public String encode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
}
