/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.adapters;

import com.rezzedup.util.valuables.Adapter;
import com.rezzedup.util.valuables.Deserializer;
import com.rezzedup.util.valuables.Serializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;
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
            public Optional<V> deserialize(String serialized) { return deserializer.deserialize(serialized); }
    
            @Override
            public Optional<String> serialize(V deserialized) { return serializer.serialize(deserialized); }
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
