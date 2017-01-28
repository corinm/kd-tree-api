/*
 * Copied as-is from here: http://www.codingeek.com/java/io/object-streams-serialization-deserialization-java-example-serializable-interface/
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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