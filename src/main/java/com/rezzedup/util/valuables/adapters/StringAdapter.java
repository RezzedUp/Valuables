package com.rezzedup.util.valuables.adapters;

import com.rezzedup.util.valuables.Adapter;
import com.rezzedup.util.valuables.Deserializer;
import com.rezzedup.util.valuables.Serializer;
import pl.tlinkowski.annotation.basic.NullOr;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

public interface StringAdapter<V> extends Adapter<String, V>
{
    static <V> StringAdapter<V> adapts(Deserializer<String, V> deserializer, Serializer<V, String> serializer)
    {
        Objects.requireNonNull(deserializer, "deserializer");
        Objects.requireNonNull(serializer, "serializer");
        
        return new StringAdapter<>()
        {
            @Override
            public @NullOr V deserialize(String serialized) { return deserializer.deserialize(serialized); }
    
            @Override
            public @NullOr String serialize(V deserialized) { return serializer.serialize(deserialized); }
        };
    }
    
    static StringAdapter<Boolean> booleans() { return StringAdapters.BOOLEAN; }
    
    static StringAdapter<Character> characters() { return StringAdapters.CHARACTER; }
    
    static StringAdapter<Byte> bytes() { return StringAdapters.BYTE; }
    
    static StringAdapter<Short> shorts() { return StringAdapters.SHORT; }
    
    static StringAdapter<Integer> integers() { return StringAdapters.INTEGER; }
    
    static StringAdapter<Long> longs() { return StringAdapters.LONG; }
    
    static StringAdapter<Float> floats() { return StringAdapters.FLOAT; }
    
    static StringAdapter<Double> doubles() { return StringAdapters.DOUBLE; }
    
    static StringAdapter<BigInteger> bigIntegers() { return StringAdapters.BIG_INTEGER; }
    
    static StringAdapter<BigDecimal> bigDecimals() { return StringAdapters.BIG_DECIMAL; }
    
    static StringAdapter<UUID> uuids() { return StringAdapters.U_UID; }
}
