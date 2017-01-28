/*
 * Copied as-is from here: http://www.codingeek.com/java/io/object-streams-serialization-deserialization-java-example-serializable-interface/
 */

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

class Serialiser {

    static byte[] serialise(Serializable obj) {
        return SerializationUtils.serialize(obj);
    }

    static Object deserialise(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

}