/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables.adapters;

import com.rezzedup.util.valuables.Adapter;
import com.rezzedup.util.valuables.Deserializer;
import com.rezzedup.util.valuables.Serializer;

import java.util.Objects;
import java.util.Optional;

public interface StringAdapter<V> extends Adapter<String, V>
{
    static <V> StringAdapter<V> of(Deserializer<String, V> deserializer, Serializer<V, String> serializer)
    {
        Objects.requireNonNull(deserializer, "deserializer");
        Objects.requireNonNull(serializer, "serializer");
        
        return new StringAdapter<>()
        {
            @Override
            public Optional<V> deserialize(String serialized) { return deserializer.deserialize(serialized); }
    
            @Override
            public Optional<String> serialize(V deserialized) { return serializer.serialize(deserialized); }
        };
    }
    
    static StandardAdapterSet<String> adapts() { return StringAdapters.ADAPTERS; }
}
