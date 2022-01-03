/*
 * Copyright © 2021-2022, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Combines a serializer with a compatible deserializer.
 *
 * @param <S>   serialized type
 * @param <D>   deserialized type
 */
public interface Adapter<S, D> extends Deserializer<S, D>, Serializer<D, S>
{
    /**
     * Creates a new {@code Adapter} by delegating to the provided arguments.
     *
     * @param deserializer  the deserializer
     * @param serializer    the serializer
     * @param <S>           serialized type
     * @param <D>           deserialized type
     *
     * @return a new instance composed of the arguments
     */
    static <S, D> Adapter<S, D> of(Deserializer<S, D> deserializer, Serializer<D, S> serializer)
    {
        Objects.requireNonNull(deserializer, "deserializer");
        Objects.requireNonNull(serializer, "serializer");
        
        return new Adapter<>()
        {
            @Override
            public Optional<D> deserialize(S serialized) { return deserializer.deserialize(serialized); }
    
            @Override
            public Optional<S> serialize(D deserialized) { return serializer.serialize(deserialized); }
        };
    }
    
    /**
     * Gets an adapter that simply returns the input.
     *
     * @param <S>   serialized type
     *
     * @return an adapter that always returns whatever it received as input
     */
    @SuppressWarnings("unchecked")
    static <S> Adapter<S, S> identity()
    {
        return (Adapter<S, S>) Adapters.IDENTITY;
    }
    
    /**
     * Gets an adapter that always returns {@link Optional#empty()} for both deserialization and serialization.
     *
     * @param <S>   serialized type
     * @param <D>   deserialized type
     *
     * @return an adapter that always returns empty
     */
    @SuppressWarnings("unchecked")
    static <S, D> Adapter<S, D> unsupported()
    {
        return (Adapter<S, D>) Adapters.UNSUPPORTED;
    }
    
    /**
     * Adapts objects by casting if they're compatible.
     * Whether a serialized value can be cast is determined by checking the provided {@code isInstance} predicate.
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     *     Adapter<Number, Double> adapter = Adapter.cast(o -> o instanceof Double);
     *     Number serialized = adapter.serialize(5.0).orElseThrow();
     *     Double deserialized = adapter.deserialize(serialized).orElseThrow();
     * }</pre>
     *
     * @param isInstance    checks whether the serialized value is an instance of the desired type
     * @param <S>           serialized (super) type
     * @param <D>           deserialized (sub) type
     *
     * @return an adapter that deserializes to a subtype by casting
     */
    @SuppressWarnings("unchecked")
    static <S, D extends S> Adapter<S, D> cast(Predicate<S> isInstance)
    {
        return Adapter.of(
            serialized -> (isInstance.test(serialized)) ? Optional.of((D) serialized) : Optional.empty(),
            Optional::of
        );
    }
    
    /**
     * Gets standard adapters for serialized strings.
     * <p>Strings are parsed into deserialized values.</p>
     *
     * @return adapters for strings
     */
    static StandardSet<String> ofString()
    {
        return Adapters.STRINGS;
    }
    
    /**
     * Gets standard adapters for casting objects.
     *
     * <p>Objects are cast into deserialized values, with a few exceptions:</p>
     *
     * <ul>
     *     <li>{@code intoString()} converts objects into their string representation.</li>
     *     <li>All number types are compatible with each other since they all extend {@code Number}.</li>
     * </ul>
     *
     * @return adapters for objects
     */
    static StandardSet<Object> ofObject()
    {
        return Adapters.OBJECTS;
    }
    
    /**
     * A collection of standard adapters.
     *
     * @param <S>   serialized type
     */
    interface StandardSet<S>
    {
        /**
         * Adapts from the serialized type into strings and vice versa.
         *
         * @return an adapter for strings
         */
        Adapter<S, String> intoString();
        
        /**
         * Adapts from the serialized type into booleans and vice versa.
         *
         * @return an adapter for booleans
         */
        Adapter<S, Boolean> intoBoolean();
        
        /**
         * Adapts from the serialized type into characters and vice versa.
         *
         * @return an adapter for characters
         */
        Adapter<S, Character> intoCharacter();
        
        /**
         * Adapts from the serialized type into bytes and vice versa.
         *
         * @return an adapter for bytes
         */
        Adapter<S, Byte> intoByte();
        
        /**
         * Adapts from the serialized type into shorts and vice versa.
         *
         * @return an adapter for shorts
         */
        Adapter<S, Short> intoShort();
        
        /**
         * Adapts from the serialized type into integers and vice versa.
         *
         * @return an adapter for integers
         */
        Adapter<S, Integer> intoInteger();
        
        /**
         * Adapts from the serialized type into longs and vice versa.
         *
         * @return an adapter for longs
         */
        Adapter<S, Long> intoLong();
        
        /**
         * Adapts from the serialized type into floats and vice versa.
         *
         * @return an adapter for floats
         */
        Adapter<S, Float> intoFloat();
        
        /**
         * Adapts from the serialized type into doubles and vice versa.
         *
         * @return an adapter for doubles
         */
        Adapter<S, Double> intoDouble();
        
        /**
         * Adapts from the serialized type into big integers and vice versa.
         *
         * @return an adapter for big integers
         */
        Adapter<S, BigInteger> intoBigInteger();
        
        /**
         * Adapts from the serialized type into big decimals and vice versa.
         *
         * @return an adapter for big decimals
         */
        Adapter<S, BigDecimal> intoBigDecimal();
        
        /**
         * Adapts from the serialized type into UUIDs and vice versa.
         *
         * @return an adapter for UUIDs
         */
        Adapter<S, UUID> intoUuid();
        
        /**
         * Adapts from the serialized type into enums and vice versa.
         *
         * @param type  the enum class
         * @param <E>   enum type
         *
         * @return an adapter for enums
         */
        <E extends Enum<E>> Adapter<S, E> intoEnum(Class<E> type);
    }
}
