package com.hhfactory.entity.code;

public enum SmokingType implements Encodable<String> {
	OK("01", "全席喫煙可"),
	FREE("02", "全席禁煙"),
	SEPARATE("03", "分煙");

	private final String code;
	private final String name;
	private static final Decoder<String, SmokingType> decoder = Decoder.create(values());

	private SmokingType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static SmokingType decode(String code) {
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
