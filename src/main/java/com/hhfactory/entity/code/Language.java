package com.hhfactory.entity.code;

public enum Language implements Encodable<String>{
	JAPANESE("1", "日本語"),
	ENGLISH("2", "英語"),
	KOREAN("3", "韓国語"),
	CHINESE("4", "中国語");
	
	private final String code;
	private final String name;
	private static final Decoder<String, Language> decoder = Decoder.create(values());

	private Language(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Language decode(String code) {
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
