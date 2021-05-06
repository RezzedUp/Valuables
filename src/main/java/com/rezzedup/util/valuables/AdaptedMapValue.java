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

public interface AdaptedMapValue<K, O, V> extends AdaptedKeyValue<Map<K, O>, O, K, V>
{
    static <K, O, V> AdaptedMapValue<K, O, V> of(K key, KeyValueAdapter<Map<K, O>, O, K, V> adapter)
    {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(adapter, "adapter");
        
        return new AdaptedMapValue<>()
        {
            @Override
            public KeyValueAdapter<Map<K, O>, O, K, V> adapter() { return adapter; }
    
            @Override
            public K key() { return key; }
        };
    }
    
    static <K, O, V> AdaptedMapValue<K, O, V> of(K key, Adapter<O, V> adapter)
    {
        return of(key, MapAdapter.of(adapter));
    }
}
