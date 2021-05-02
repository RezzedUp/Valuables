/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.composition;

import com.rezzedup.util.valuables.Adapter;
import com.rezzedup.util.valuables.Deserializer;
import com.rezzedup.util.valuables.Serializer;
import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Objects;

public class ComposedAdapter<O, V> implements Adapter<O, V>
{
    private final Deserializer<V, O> deserializer;
    public final Serializer<V, O> serializer;
    
    public ComposedAdapter(Deserializer<V, O> deserializer, Serializer<V, O> serializer)
    {
        this.deserializer = Objects.requireNonNull(deserializer, "deserializer");
        this.serializer = Objects.requireNonNull(serializer, "serializer");
    }
    
    @Override
    public @NullOr V deserialize(O output)
    {
        return deserializer.deserialize(output);
    }
    
    @Override
    public @NullOr O serialize(V input)
    {
        return serializer.serialize(input);
    }
}
