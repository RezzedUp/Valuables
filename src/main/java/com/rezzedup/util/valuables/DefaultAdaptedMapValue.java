/*
 * Copyright © 2021, RezzedUp <https://github.com/RezzedUp/Valuables>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rezzedup.util.valuables;

import java.util.Map;
import java.util.Objects;

public interface DefaultAdaptedMapValue<K, O, V> extends AdaptedMapValue<K, O, V>, DefaultAdaptedKeyValue<Map<K, O>, O, K, V>
{
    static <K, O, V> DefaultAdaptedMapValue<K, O, V> of(V def, AdaptedMapValue<K, O, V> value)
    {
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(value, "value");
        
        return new DefaultAdaptedMapValue<>()
        {
            @Override
            public KeyValueAdapter<Map<K, O>, O, K, V> adapter() { return value.adapter(); }
    
            @Override
            public V getDefaultValue() { return def; }
    
            @Override
            public K key() { return value.key(); }
    
            @Override
            public boolean isSet(Map<K, O> storage) { return value.isSet(storage); }
        };
    }
}
