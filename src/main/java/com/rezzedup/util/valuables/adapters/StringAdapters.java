/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.adapters;

import com.rezzedup.util.valuables.Deserializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

final class StringAdapters
{
    private StringAdapters() { throw new UnsupportedOperationException(); }
    
    static final StringAdapter<Boolean> BOOLEAN =
        simple(serialized ->
            ("true".equalsIgnoreCase(serialized))
                ? Boolean.TRUE
                : ("false".equalsIgnoreCase(serialized))
                    ? Boolean.FALSE
                    : null
        );
    
    static final StringAdapter<Character> CHARACTER =
        simple(serialized -> (serialized.length() == 1) ? serialized.charAt(0) : null);
    
    static final StringAdapter<Short> SHORT = parse(Short::parseShort);
    
    static final StringAdapter<Byte> BYTE = parse(Byte::parseByte);
    
    static final StringAdapter<Integer> INTEGER = parse(Integer::parseInt);
    
    static final StringAdapter<Long> LONG = parse(Long::parseLong);
    
    static final StringAdapter<Float> FLOAT = parse(Float::parseFloat);
    
    static final StringAdapter<Double> DOUBLE = parse(Double::parseDouble);
    
    static final StringAdapter<BigInteger> BIG_INTEGER = parse(BigInteger::new);
    
    static final StringAdapter<BigDecimal> BIG_DECIMAL = parse(BigDecimal::new);
    
    static final StringAdapter<UUID> U_UID = parse(UUID::fromString);
    
    private static <V> StringAdapter<V> simple(Deserializer<String, V> deserializer)
    {
        return StringAdapter.adapts(deserializer, String::valueOf);
    }
    
    private static <V> StringAdapter<V> parse(Deserializer<String, V> deserializer)
    {
        return simple(serialized -> {
            try { return deserializer.deserialize(serialized); }
            catch (RuntimeException ignored) { return null; }
        });
    }
}
