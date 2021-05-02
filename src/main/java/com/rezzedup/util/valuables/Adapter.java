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

public interface Adapter<O, V> extends Deserializer<V, O>, Serializer<V, O>
{
    static <O, V> Adapter<O, V> of(Deserializer<V, O> deserializer, Serializer<V, O> serializer)
    {
        Objects.requireNonNull(deserializer, "deserializer");
        Objects.requireNonNull(serializer, "serializer");
        
        return new Adapter<O, V>()
        {
            @Override
            public @NullOr V deserialize(O output) { return deserializer.deserialize(output); }
    
            @Override
            public @NullOr O serialize(V input) { return serializer.serialize(input); }
        };
    }
    
    @SuppressWarnings("unchecked")
    static <V> Adapter<V, V> identity()
    {
        return (Adapter<V, V>) Adapters.IDENTITY;
    }
    
    static <O, V extends O> Adapter<O, V> subtype(Deserializer<V, O> deserializer)
    {
        return of(
            output -> {
                try { return deserializer.deserialize(output); }
                catch (ClassCastException e) { return null; }
            },
            val -> (O) val
        );
    }
}
