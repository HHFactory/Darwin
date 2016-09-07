package com.hhfactory.entity.code;

public enum Prefecture implements Encodable<String>{
	HOKKAIDO("1", "北海道")
	;

	private final String code;
	private final String name;
	private static final Decoder<String, Prefecture> decoder = Decoder.create(values());
	
	private Prefecture(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	@Override
	public String encode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public static Prefecture decode(String code) {
		return decoder.decode(code);
	}

}
