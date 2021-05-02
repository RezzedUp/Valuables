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

public interface Adapter<O, V> extends Deserializer<O, V>, Serializer<V, O>
{
    static <O, V> Adapter<O, V> of(Deserializer<O, V> deserializer, Serializer<V, O> serializer)
    {
        Objects.requireNonNull(deserializer, "deserializer");
        Objects.requireNonNull(serializer, "serializer");
        
        return new Adapter<O, V>()
        {
            @Override
            public @NullOr V deserialize(O serialized) { return deserializer.deserialize(serialized); }
    
            @Override
            public @NullOr O serialize(V deserialized) { return serializer.serialize(deserialized); }
        };
    }
    
    @SuppressWarnings("unchecked")
    static <V> Adapter<V, V> identity()
    {
        return (Adapter<V, V>) Adapters.IDENTITY;
    }
    
    static <O, V extends O> Adapter<O, V> subtype(Deserializer<O, V> deserializer)
    {
        return of(
            serialized -> {
                try { return deserializer.deserialize(serialized); }
                catch (ClassCastException e) { return null; }
            },
            deserialized -> (O) deserialized
        );
    }
}
