/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

/**
 * Predefined standard adapter sets.
 */
public class Adapts
{
    private Adapts() { throw new UnsupportedOperationException(); }
    
    static final Adapter<?, ?> IDENTITY = new Adapter<>()
    {
        @Override
        public Optional<Object> deserialize(Object serialized) { return Optional.of(serialized); }
    
        @Override
        public Optional<Object> serialize(Object deserialized) { return Optional.of(deserialized); }
    };
    
    @SuppressWarnings("unchecked")
    static <S> Adapter<S, S> identity() { return (Adapter<S, S>) IDENTITY; }
    
    private static final StandardStringAdapters STRINGS = new StandardStringAdapters();
    
    /**
     * Gets the standard adapter set for serialized strings.
     *
     * @return  adapters for strings
     */
    public static Adapter.StandardSet<String> string() { return STRINGS; }
    
    private static class StandardStringAdapters implements Adapter.StandardSet<String>
    {
        private StandardStringAdapters() { throw new UnsupportedOperationException(); }
        
        static final Adapter<String, Boolean> BOOLEAN =
            simple(serialized ->
                ("true".equalsIgnoreCase(serialized))
                    ? Optional.of(Boolean.TRUE)
                    : ("false".equalsIgnoreCase(serialized))
                        ? Optional.of(Boolean.FALSE)
                        : Optional.empty()
            );
        
        static final Adapter<String, Character> CHARACTER =
            simple(serialized ->
                (serialized.length() == 1)
                    ? Optional.of(serialized.charAt(0))
                    : Optional.empty()
            );
        
        static final Adapter<String, Short> SHORT = parse(Short::parseShort);
        
        static final Adapter<String, Byte> BYTE = parse(Byte::parseByte);
        
        static final Adapter<String, Integer> INTEGER = parse(Integer::parseInt);
        
        static final Adapter<String, Long> LONG = parse(Long::parseLong);
        
        static final Adapter<String, Float> FLOAT = parse(Float::parseFloat);
        
        static final Adapter<String, Double> DOUBLE = parse(Double::parseDouble);
        
        static final Adapter<String, BigInteger> BIG_INTEGER = parse(BigInteger::new);
        
        static final Adapter<String, BigDecimal> BIG_DECIMAL = parse(BigDecimal::new);
        
        static final Adapter<String, UUID> U_UID = parse(UUID::fromString);
        
        private static <V> Adapter<String, V> simple(Deserializer<String, V> deserializer)
        {
            return Adapter.of(deserializer, deserialized -> Optional.of(String.valueOf(deserialized)));
        }
        
        private static <V> Adapter<String, V> parse(Function<String, V> parser)
        {
            return simple(serialized -> {
                try { return Optional.of(parser.apply(serialized)); }
                catch (RuntimeException ignored) { return Optional.empty(); }
            });
        }
        
        @Override
        public Adapter<String, String> intoString() { return identity(); }
        
        @Override
        public Adapter<String, Boolean> intoBoolean() { return BOOLEAN; }
        
        @Override
        public Adapter<String, Character> intoCharacter() { return CHARACTER; }
        
        @Override
        public Adapter<String, Byte> intoByte() { return BYTE; }
        
        @Override
        public Adapter<String, Short> intoShort() { return SHORT; }
        
        @Override
        public Adapter<String, Integer> intoInteger() { return INTEGER; }
        
        @Override
        public Adapter<String, Long> intoLong() { return LONG; }
        
        @Override
        public Adapter<String, Float> intoFloat() { return FLOAT; }
        
        @Override
        public Adapter<String, Double> intoDouble() { return DOUBLE; }
        
        @Override
        public Adapter<String, BigInteger> intoBigInteger() { return BIG_INTEGER; }
        
        @Override
        public Adapter<String, BigDecimal> intoBigDecimal() { return BIG_DECIMAL; }
        
        @Override
        public Adapter<String, UUID> intoUuid() { return U_UID; }
    }
}
