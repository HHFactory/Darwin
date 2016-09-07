package com.hhfactory.entity.code;

public enum HolidayType implements Encodable<String> {
	SUNDAY("1", "日曜定休"),
	MONDAY("", "月曜定休"),
	TUESDAY("", "火曜定休"),
	WEDNESDAY("", "水曜定休"),
	THURSDAY("", "木曜定休"),
	FRIDAY("", "金曜定休"),
	SATURDAY("", "土曜定休"),
	PUBLIC_HOLIDAY("", "祝日定休"),
	ALL_HOLIDAY("", "土日祝定休");

	private final String code;
	private final String name;
	private static final Decoder<String, HolidayType> decoder = Decoder.create(values());

	private HolidayType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static HolidayType decode(String code) {
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
