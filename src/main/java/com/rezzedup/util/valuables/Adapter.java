/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;

public interface Adapter<S, D> extends Deserializer<S, D>, Serializer<D, S>
{
    static <S, D> Adapter<S, D> adapts(Deserializer<S, D> deserializer, Serializer<D, S> serializer)
    {
        Objects.requireNonNull(deserializer, "deserializer");
        Objects.requireNonNull(serializer, "serializer");
        
        return new Adapter<>()
        {
            @Override
            public @NullOr D deserialize(S serialized) { return deserializer.deserialize(serialized); }
    
            @Override
            public @NullOr S serialize(D deserialized) { return serializer.serialize(deserialized); }
        };
    }
    
    @SuppressWarnings("unchecked")
    static <S> Adapter<S, S> identity()
    {
        return (Adapter<S, S>) Adapters.IDENTITY;
    }
    
    static <S, D extends S> Adapter<S, D> subtype(Deserializer<S, D> deserializer)
    {
        return adapts(
            serialized -> {
                try { return deserializer.deserialize(serialized); }
                catch (ClassCastException e) { return null; }
            },
            deserialized -> deserialized
        );
    }
}
