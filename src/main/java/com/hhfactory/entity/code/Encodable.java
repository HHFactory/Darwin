package com.hhfactory.entity.code;

import java.io.Serializable;

public interface Encodable<T extends Serializable> {
	public T encode();
}
