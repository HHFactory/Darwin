package com.hhfactory.entity.code;

public enum ImageCategory implements Encodable<Integer> {
	INSIDE(1, "内観"),
	OUTSIDE(2, "外観");

	private final Integer code;
	private final String name;
	private static final Decoder<Integer, ImageCategory> decoder = Decoder.create(values());

	private ImageCategory(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static ImageCategory decode(Integer code) {
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
