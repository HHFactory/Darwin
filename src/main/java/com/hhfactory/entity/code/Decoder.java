package com.hhfactory.entity.code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Decoder<K extends Serializable, V extends Encodable<K>> {
	private Map<K, V> map;
	
	private Decoder(V[] values) {
        map = new HashMap<K, V>(values.length);

        for (V value : values) {
            V old = map.put(value.encode(), value);

            // コード値の重複はサポートしない
            if (old != null) {
                throw new IllegalArgumentException("duplicated code: " + value);
            }
        }
    }

    public V decode(K code) {
        return map.get(code);
    }

    public static <K1 extends Serializable, V1 extends Encodable<K1>> Decoder<K1, V1> create(V1[] values) {
        return new Decoder<K1, V1>(values);
    }
    
}
