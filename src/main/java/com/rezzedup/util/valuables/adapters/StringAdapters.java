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
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

final class StringAdapters
{
    private StringAdapters() { throw new UnsupportedOperationException(); }
    
    static final StringAdapter<Boolean> BOOLEAN =
        simple(serialized ->
            ("true".equalsIgnoreCase(serialized))
                ? Optional.of(Boolean.TRUE)
                : ("false".equalsIgnoreCase(serialized))
                    ? Optional.of(Boolean.FALSE)
                    : Optional.empty()
        );
    
    static final StringAdapter<Character> CHARACTER =
        simple(serialized ->
            (serialized.length() == 1)
                ? Optional.of(serialized.charAt(0))
                : Optional.empty()
        );
    
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
        return StringAdapter.of(deserializer, deserialized -> Optional.of(String.valueOf(deserialized)));
    }
    
    private static <V> StringAdapter<V> parse(Function<String, V> parser)
    {
        return simple(serialized -> {
            try { return Optional.of(parser.apply(serialized)); }
            catch (RuntimeException ignored) { return Optional.empty(); }
        });
    }
}
