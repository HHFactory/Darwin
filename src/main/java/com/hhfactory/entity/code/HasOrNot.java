package com.hhfactory.entity.code;

public enum HasOrNot implements Encodable<String> {
	HAS("1", "ある"),
	NOTHAS("2", "ない");

	private final String code;
	private final String name;
	private static final Decoder<String, HasOrNot> decoder = Decoder.create(values());

	private HasOrNot(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static HasOrNot decode(String code) {
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
