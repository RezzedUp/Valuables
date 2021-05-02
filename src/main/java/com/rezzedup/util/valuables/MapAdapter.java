/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import pl.tlinkowski.annotation.basic.NullOr;

import java.util.Map;
import java.util.Objects;

public interface MapAdapter<K, O, V> extends KeyValueAdapter<Map<K, O>, O, K, V>
{
    static <K, O, V> MapAdapter<K, O, V> of(Adapter<O, V> adapter)
    {
        Objects.requireNonNull(adapter, "adapter");
        
        return new MapAdapter<>()
        {
            @Override
            public @NullOr V deserialize(O output) { return adapter.deserialize(output); }
    
            @Override
            public @NullOr O serialize(V input) { return adapter.serialize(input); }
        };
    }
    
    @Override
    default @NullOr V get(Map<K, O> storage, K key)
    {
        @NullOr O input = storage.get(key);
        return (input == null) ? null : deserialize(input);
    }
    
    @Override
    default void set(Map<K, O> storage, K key, @NullOr V value)
    {
        @NullOr O output = (value == null) ? null : serialize(value);
        if (output == null) { storage.remove(key); }
        else { storage.put(key, output); }
    }
}
