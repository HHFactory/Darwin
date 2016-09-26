package com.hhfactory.entity.code;

public enum CreaditCard implements Encodable<String>{
	VISA("1", "VISA"),
	MASTER("2", "MASTER"),
	JCB("3", "JCB"),
	AMEX("4", "AMEX");
	
	private final String code;
	private final String name;
	private static final Decoder<String, CreaditCard> decoder = Decoder.create(values());
	
	private CreaditCard(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static CreaditCard decode(String code) {
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
