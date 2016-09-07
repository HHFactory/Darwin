package com.hhfactory.entity.code;

public enum RestaurantStatus implements Encodable<String> {
	OPEN("1", "有効"),
	CLOSED("2", "閉店");

	private final String code;
	private final String name;
	private static final Decoder<String, RestaurantStatus> decoder = Decoder.create(values());

	private RestaurantStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static RestaurantStatus decode(String code) {
		return decoder.decode(code);
	}

	@Override
	public String encode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

}
