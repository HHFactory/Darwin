package com.hhfactory.entity.code;

public enum FoodType implements Encodable<Integer> {
	GATTURI(1, "がっつり"), 
	ASSARI(2, "あっさり"), 
	SPPEDY(3, "はやい");

	private final Integer code;
	private final String name;
	private static final Decoder<Integer, FoodType> decoder = Decoder.create(values());

	private FoodType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static FoodType decode(Integer code) {
		return decoder.decode(code);
	}

	@Override
	public Integer encode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
