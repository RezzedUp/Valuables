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

public interface Adapter<S, D> extends Deserializer<S, D>, Serializer<D, S>
{
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
    
    @SuppressWarnings("unchecked")
    static <S> Adapter<S, S> identity()
    {
        return (Adapter<S, S>) Adapters.IDENTITY;
    }
    
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
