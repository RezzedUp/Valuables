/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Combines a serializer with a compatible deserializer.
 *
 * @param <S>   serialized type
 * @param <D>   deserialized type
 */
public interface Adapter<S, D> extends Deserializer<S, D>, Serializer<D, S>
{
    /**
     * Creates a new {@code Adapter} by delegating
     * to the provided arguments.
     *
     * @param deserializer  the deserializer
     * @param serializer    the serializer
     * @param <S>           serialized type
     * @param <D>           deserialized type
     *
     * @return  a new instance composed of the arguments
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
     * @return  an adapter that always returns whatever
     *          it received as input
     */
    @SuppressWarnings("unchecked")
    static <S> Adapter<S, S> identity()
    {
        return (Adapter<S, S>) Adapters.IDENTITY;
    }
    
    /**
     * Helper for creating adapters that deserialize objects
     * by casting them to a compatible subtype.
     *
     * <p><b>Example:</b></p>
     * <pre>
     *     Adapter&lt;Number, Double&gt; adapter = Adapter.subtype(num -> (Double) num);
     *     Number serialized = adapter.serialize(5.0).orElseThrow();
     *     // deserializes by casting to a subtype:
     *     Double deserialized = adapter.deserialize(serialized).orElseThrow();
     * </pre>
     *
     * @param deserializer  the cast to a subtype
     * @param <S>           serialized (super) type
     * @param <D>           deserialized (sub) type
     *
     * @return  an adapter that deserializes to a subtype
     */
    static <S, D extends S> Adapter<S, D> subtype(Function<S, D> deserializer)
    {
        return Adapter.of(
            serialized -> {
                try { return Optional.of(deserializer.apply(serialized)); }
                catch (ClassCastException e) { return Optional.empty(); }
            },
            Optional::of
        );
    }
}
