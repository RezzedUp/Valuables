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
import java.util.Optional;

public interface MapValue<K, V> extends KeyValue<Map<K, V>, K, V>
{
    static <K, V> MapValue<K, V> of(K key)
    {
        Objects.requireNonNull(key, "key");
        
        return new MapValue<>()
        {
            @Override
            public K key() { return key; }
    
            @Override
            public Optional<V> get(Map<K, V> storage) { return Optional.ofNullable(storage.get(key)); }
    
            @Override
            public void set(Map<K, V> storage, @NullOr V value)
            {
                if (value == null) { storage.remove(key); }
                else { storage.put(key, value); }
            }
        };
    }
}
