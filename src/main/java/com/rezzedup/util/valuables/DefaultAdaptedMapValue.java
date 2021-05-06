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
    static <K, O, V> DefaultAdaptedMapValue<K, O, V> of(K key, V def, DelegatedKeyAdapter<Map<K, O>, O, K, V> adapter)
    {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(def, "def");
        Objects.requireNonNull(adapter, "adapter");
        
        return new DefaultAdaptedMapValue<>()
        {
            @Override
            public DelegatedKeyAdapter<Map<K, O>, O, K, V> adapter() { return adapter; }
    
            @Override
            public V getDefaultValue() { return def; }
    
            @Override
            public K key() { return key; }
        };
    }
    
    static <K, O, V> DefaultAdaptedMapValue<K, O, V> of(K key, V def, Adapter<O, V> adapter)
    {
        return of(key, def, DelegatedMapAdapter.of(adapter));
    }
}
