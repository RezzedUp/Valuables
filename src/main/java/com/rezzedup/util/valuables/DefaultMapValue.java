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

public interface DefaultMapValue<K, V> extends DefaultKeyValue<Map<K, V>, K, V>, MapValue<K, V>
{
    static <K, V> DefaultMapValue<K, V> defaults(K key, V def)
    {
        MapValue<K, V> value = MapValue.where(key);
        Objects.requireNonNull(def, "def");
        
        return new DefaultMapValue<>()
        {
            @Override
            public K key() { return value.key(); }
    
            @Override
            public V getDefaultValue() { return def; }
    
            @Override
            public Optional<V> get(Map<K, V> storage) { return value.get(storage); }
    
            @Override
            public void set(Map<K, V> storage, @NullOr V updated) { value.set(storage, updated); }
        };
    }
}
