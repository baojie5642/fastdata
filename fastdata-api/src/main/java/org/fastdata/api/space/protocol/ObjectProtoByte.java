package org.fastdata.api.space.protocol;

public interface ObjectProtoByte {

	<T> byte[] toBytes(final T object);
	
}
